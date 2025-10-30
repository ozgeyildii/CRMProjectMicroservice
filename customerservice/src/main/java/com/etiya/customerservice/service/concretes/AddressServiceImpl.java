package com.etiya.customerservice.service.concretes;

import com.etiya.common.events.address.CreateAddressEvent;
import com.etiya.common.events.address.DeleteAddressEvent;
import com.etiya.common.events.address.SoftDeleteAddressEvent;
import com.etiya.common.events.address.UpdateAddressEvent;
import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.domain.entities.District;
import com.etiya.customerservice.repository.AddressRepository;
import com.etiya.customerservice.service.abstracts.AddressService;
import com.etiya.customerservice.service.abstracts.CustomerService;
import com.etiya.customerservice.service.abstracts.DistrictService;
import com.etiya.customerservice.service.abstracts.IndividualCustomerService;
import com.etiya.customerservice.service.mappings.AddressMapper;
import com.etiya.customerservice.service.requests.address.CreateAddressRequest;
import com.etiya.customerservice.service.requests.address.UpdateAddressRequest;
import com.etiya.customerservice.service.requests.addressorchestratorrequest.CreateFullAddressRequest;
import com.etiya.customerservice.service.responses.address.CreatedAddressResponse;
import com.etiya.customerservice.service.responses.address.GetByIdAddressResponse;
import com.etiya.customerservice.service.responses.address.GetListAddressResponse;
import com.etiya.customerservice.service.responses.address.UpdatedAddressResponse;
import com.etiya.customerservice.service.rules.AddressBusinessRules;
import com.etiya.customerservice.transport.kafka.producer.address.CreateAddressProducer;
import com.etiya.customerservice.transport.kafka.producer.address.DeleteAddressProducer;
import com.etiya.customerservice.transport.kafka.producer.address.SoftDeleteAddressProducer;
import com.etiya.customerservice.transport.kafka.producer.address.UpdateAddressProducer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final IndividualCustomerService individualCustomerService;
    private final DistrictService districtService;
    private final AddressBusinessRules addressBusinessRules;
    private final CreateAddressProducer createAddressProducer;
    private final UpdateAddressProducer updateAddressProducer;
    private final DeleteAddressProducer deleteAddressProducer;
    private final SoftDeleteAddressProducer softDeleteAddressProducer;

    public AddressServiceImpl(AddressRepository addressRepository, CustomerService customerService, IndividualCustomerService individualCustomerService, DistrictService districtService, AddressBusinessRules addressBusinessRules, CreateAddressProducer createAddressProducer, UpdateAddressProducer updateAddressProducer, DeleteAddressProducer deleteAddressProducer, SoftDeleteAddressProducer softDeleteAddressProducer) {
        this.individualCustomerService = individualCustomerService;
        this.districtService = districtService;
        this.addressBusinessRules  = addressBusinessRules;
        this.addressRepository = addressRepository;
        this.createAddressProducer = createAddressProducer;
        this.updateAddressProducer = updateAddressProducer;
        this.deleteAddressProducer = deleteAddressProducer;
        this.softDeleteAddressProducer = softDeleteAddressProducer;
    }

   // @Override
   // public void add(Address address) {
   //     addressRepository.save(address);
   // }

    @Override
    public CreatedAddressResponse add(CreateAddressRequest request) {
       Address address = AddressMapper.INSTANCE.addressFromCreateAddressRequest(request);
       Address createdAddress = addressRepository.save(address);
       CreateAddressEvent event = new CreateAddressEvent(createdAddress.getId(),
               createdAddress.getStreet(),
               createdAddress.getHouseNumber(),
               createdAddress.getDescription(),
       createdAddress.isDefault(),
       createdAddress.getDistrict().getId(),
       createdAddress.getCustomer().getId());
       createAddressProducer.produceAddressCreated(event);

       CreatedAddressResponse response = AddressMapper.INSTANCE.createdAddressResponseFromAddress(createdAddress);
        return response;
    }

    @Override
    public List<GetListAddressResponse> getList() {
        List<Address> addressList = addressRepository.findAll();
        List<GetListAddressResponse> response = AddressMapper.INSTANCE.getListAddressResponsesFromAddressList(addressList);
        return response;
    }

    @Override
    public void delete(int id) { //kalıcı silme- hard delete
        Address address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
        addressBusinessRules.checkIfBillingAccountExists(id);
        addressRepository.deleteById(id);
        DeleteAddressEvent event=new DeleteAddressEvent(id, address.getCustomer().getId());
        deleteAddressProducer.produceAddressDeleted(event);
    }

    @Override
    public void softDelete(int id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
        address.setDeletedDate(LocalDateTime.now());
        addressRepository.save(address);
        SoftDeleteAddressEvent event=new SoftDeleteAddressEvent(id, address.getCustomer().getId(),address.getDeletedDate().toString());
        softDeleteAddressProducer.produceAddressSoftDeleted(event);
    }

    @Override
    public UpdatedAddressResponse update(UpdateAddressRequest request) {
        Address oldAddress = addressRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Address not found"));
        Address address =  AddressMapper.INSTANCE.addressFromUpdateAddressRequest(request,oldAddress);
        Address updatedAddress = addressRepository.save(address);
        UpdateAddressEvent event = new UpdateAddressEvent(updatedAddress.getId(),
                updatedAddress.getStreet(),
                updatedAddress.getHouseNumber(),
                updatedAddress.getDescription(),
                updatedAddress.isDefault(),
                updatedAddress.getDistrict().getId(),
                updatedAddress.getCustomer().getId());
        updateAddressProducer.produceAddressUpdated(event);
        UpdatedAddressResponse response = AddressMapper.INSTANCE.updatedAddressResponseFromAddress(updatedAddress);
        return response;
    }

    @Override
    public GetByIdAddressResponse getById(int id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
        GetByIdAddressResponse response = AddressMapper.INSTANCE.getAddressResponseFromAddress(address);
        return response;
    }

    @Override
    public void addForCustomer(UUID customerId, CreateFullAddressRequest request) {
        Address address = new Address();
        address.setStreet(request.getStreet());
        address.setHouseNumber(request.getHouseNumber());
        address.setDescription(request.getDescription());
        address.setDefault(request.isDefault());
        District district = districtService.findByName(request.getDistrict());
        address.setDistrict(district);
        address.setCustomer(individualCustomerService.findById(customerId));
        addressRepository.save(address);
    }


/*
    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }*/


}
