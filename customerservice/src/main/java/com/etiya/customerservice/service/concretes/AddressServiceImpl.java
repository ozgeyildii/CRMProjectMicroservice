package com.etiya.customerservice.service.concretes;

import com.etiya.common.events.address.CreateAddressEvent;
import com.etiya.common.events.address.DeleteAddressEvent;
import com.etiya.common.events.address.SoftDeleteAddressEvent;
import com.etiya.common.events.address.UpdateAddressEvent;
import com.etiya.common.responses.GetAddressResponse;
import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.domain.entities.Customer;
import com.etiya.customerservice.domain.entities.District;
import com.etiya.customerservice.infrastructure.OutboxService;
import com.etiya.customerservice.repository.AddressRepository;
import com.etiya.customerservice.service.abstracts.AddressService;
import com.etiya.customerservice.service.abstracts.CustomerService;
import com.etiya.customerservice.service.abstracts.DistrictService;
import com.etiya.customerservice.service.mappings.AddressMapper;
import com.etiya.customerservice.service.requests.address.CreateAddressRequest;
import com.etiya.customerservice.service.requests.address.UpdateAddressRequest;
import com.etiya.customerservice.service.responses.address.CreatedAddressResponse;
import com.etiya.customerservice.service.responses.address.GetByIdAddressResponse;
import com.etiya.customerservice.service.responses.address.GetListAddressResponse;
import com.etiya.customerservice.service.responses.address.UpdatedAddressResponse;
import com.etiya.customerservice.service.rules.AddressBusinessRules;
import com.etiya.customerservice.transport.kafka.producer.address.DeleteAddressProducer;
import com.etiya.customerservice.transport.kafka.producer.address.SoftDeleteAddressProducer;
import com.etiya.customerservice.transport.kafka.producer.address.UpdateAddressProducer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final DistrictService districtService;
    private final CustomerService customerService;
    private final AddressBusinessRules addressBusinessRules;
    private final UpdateAddressProducer updateAddressProducer;
    private final DeleteAddressProducer deleteAddressProducer;
    private final SoftDeleteAddressProducer softDeleteAddressProducer;
    private final OutboxService outboxService;

    public AddressServiceImpl(AddressRepository addressRepository, CustomerService customerService, DistrictService districtService, AddressBusinessRules addressBusinessRules, UpdateAddressProducer updateAddressProducer, DeleteAddressProducer deleteAddressProducer, SoftDeleteAddressProducer softDeleteAddressProducer, OutboxService outboxService) {
        this.districtService = districtService;
        this.customerService = customerService;
        this.addressBusinessRules = addressBusinessRules;
        this.addressRepository = addressRepository;
        this.updateAddressProducer = updateAddressProducer;
        this.deleteAddressProducer = deleteAddressProducer;
        this.softDeleteAddressProducer = softDeleteAddressProducer;
        this.outboxService = outboxService;
    }


    @Override
    public CreatedAddressResponse add(CreateAddressRequest request) {
        District district = districtService.getDistrictById(request.getDistrictId());
        Customer customer = customerService.getByEntityId(request.getCustomerId());

        Address address = AddressMapper.INSTANCE.addressFromCreateAddressRequest(request);
        address.setCustomer(customer);
        address.setDistrict(district);

        Address createdAddress = addressRepository.save(address);

        CreateAddressEvent event = new CreateAddressEvent(
                createdAddress.getId(),
                createdAddress.getStreet(),
                createdAddress.getHouseNumber(),
                createdAddress.getDescription(),
                createdAddress.isDefault(),
                createdAddress.getDistrict().getId(),
                createdAddress.getDistrict().getName(),
                createdAddress.getDistrict().getCity().getId(),
                createdAddress.getDistrict().getCity().getName(),
                createdAddress.getCustomer().getId()
        );

        outboxService.save(event, "ADDRESS", createdAddress.getCustomer().getId().toString());

        return AddressMapper.INSTANCE.createdAddressResponseFromAddress(createdAddress);
    }


    @Override
    public List<GetListAddressResponse> getList() {
        List<Address> addressList = addressRepository.findAll();
        List<GetListAddressResponse> response = AddressMapper.INSTANCE.getListAddressResponsesFromAddressList(addressList);
        return response;
    }

    @Override
    public void delete(int id) {
        Address address = addressBusinessRules.getAddressOrThrowException(id);
        addressBusinessRules.checkIfPrimaryAddressExists(id);
        addressBusinessRules.checkIfBillingAccountExists(id);
        addressRepository.deleteById(id);
        DeleteAddressEvent event = new DeleteAddressEvent(id, address.getCustomer().getId());
        deleteAddressProducer.produceAddressDeleted(event);
    }

    @Override
    public void softDelete(int id) {
        Address address = addressBusinessRules.getAddressOrThrowException(id);
        address.setDeletedDate(LocalDateTime.now());
        addressRepository.save(address);
        SoftDeleteAddressEvent event = new SoftDeleteAddressEvent(id, address.getCustomer().getId(), address.getDeletedDate().toString());
        softDeleteAddressProducer.produceAddressSoftDeleted(event);
    }

    @Override
    public UpdatedAddressResponse update(UpdateAddressRequest request) {
        Address oldAddress = addressBusinessRules.getAddressOrThrowException(request.getId());
        Address address = AddressMapper.INSTANCE.addressFromUpdateAddressRequest(request, oldAddress);

        District district = districtService.getDistrictById(request.getDistrictId());
        address.setDistrict(district);

        Address savedAddress = addressRepository.save(address);

        UpdateAddressEvent event = new UpdateAddressEvent(
                savedAddress.getId(),
                savedAddress.getStreet(),
                savedAddress.getHouseNumber(),
                savedAddress.getDescription(),
                savedAddress.isDefault(),
                savedAddress.getDistrict().getId(),
                savedAddress.getDistrict().getName(),
                savedAddress.getDistrict().getCity().getId(),
                savedAddress.getDistrict().getCity().getName(),
                savedAddress.getCustomer().getId()
        );

        updateAddressProducer.produceAddressUpdated(event);

        UpdatedAddressResponse response = AddressMapper.INSTANCE.updatedAddressResponseFromAddress(savedAddress);
        return response;
    }


    @Override
    public GetByIdAddressResponse getById(int id) {
        Address address = addressBusinessRules.getAddressOrThrowException(id);
        GetByIdAddressResponse response = AddressMapper.INSTANCE.getAddressResponseFromAddress(address);
        return response;
    }

    @Override
    public List<GetListAddressResponse> getListByCustomerId(UUID customerId) {
        List<Address> addressList = addressRepository.findByCustomer_Id(customerId);
        List<GetListAddressResponse> response = AddressMapper.INSTANCE.getListAddressResponsesFromAddressList(addressList);
        return response;
    }

    @Override
    @Transactional
    public void setPrimaryAddress(int id) {

        Address selectedAddress = addressBusinessRules.getAddressOrThrowException(id);

        List<Address> updatedAddresses = addressRepository
                .findByCustomer_Id(selectedAddress.getCustomer().getId())
                .stream()
                .peek(address -> address.setDefault(address.getId() == id))
                .toList();

        addressRepository.saveAll(updatedAddresses);

    }

    @Override
    public GetAddressResponse getAddressById(int id) {
        Address address = addressBusinessRules.getAddressOrThrowException(id);

        return AddressMapper.INSTANCE.addressToGetAddressResponse(address);
    }

/*
    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }*/


}
