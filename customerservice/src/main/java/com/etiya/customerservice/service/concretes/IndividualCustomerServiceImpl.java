package com.etiya.customerservice.service.concretes;

import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;

import com.etiya.common.events.customer.DeleteCustomerEvent;
import com.etiya.common.events.customer.UpdateCustomerEvent;
import com.etiya.common.responses.CustomerResponse;

import com.etiya.customerservice.domain.entities.Customer;
import com.etiya.customerservice.domain.entities.IndividualCustomer;

import com.etiya.customerservice.repository.IndividualCustomerRepository;
import com.etiya.customerservice.service.abstracts.IndividualCustomerService;
import com.etiya.customerservice.service.mappings.IndividualCustomerMapper;
import com.etiya.customerservice.service.requests.individualcustomers.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.requests.individualcustomers.UpdateIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.customer.AddressResponse;
import com.etiya.customerservice.service.responses.customer.ContactMediumResponse;
import com.etiya.customerservice.service.responses.customer.FullCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomers.CreatedIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomers.UpdatedIndividualCustomerResponse;
import com.etiya.customerservice.service.rules.IndividualCustomerBusinessRules;
import com.etiya.customerservice.transport.kafka.producer.customer.CreateCustomerProducer;
import com.etiya.customerservice.transport.kafka.producer.customer.DeleteCustomerProducer;
import com.etiya.customerservice.transport.kafka.producer.customer.UpdateCustomerProducer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IndividualCustomerServiceImpl implements IndividualCustomerService {

    private final IndividualCustomerRepository individualCustomerRepository;
    private final IndividualCustomerBusinessRules individualCustomerBusinessRules;
    private final UpdateCustomerProducer updateCustomerProducer;
    private final DeleteCustomerProducer deleteCustomerProducer;

    public IndividualCustomerServiceImpl(IndividualCustomerRepository individualCustomerRepository, IndividualCustomerBusinessRules individualCustomerBusinessRules, UpdateCustomerProducer updateCustomerProducer, DeleteCustomerProducer deleteCustomerProducer) {
        this.individualCustomerRepository = individualCustomerRepository;
        this.individualCustomerBusinessRules = individualCustomerBusinessRules;
        this.updateCustomerProducer = updateCustomerProducer;
        this.deleteCustomerProducer = deleteCustomerProducer;
    }

    @Override
    public CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest request) {
        individualCustomerBusinessRules.checkIfIndividualCustomerExistsByIdentityNumber((request.getNationalId()));
        IndividualCustomer individualCustomer = IndividualCustomerMapper.INSTANCE.individualCustomerFromCreateIndividualCustomerRequest(request);
        IndividualCustomer createdIndividualCustomer = individualCustomerRepository.save(individualCustomer);

        CreatedIndividualCustomerResponse response =
                IndividualCustomerMapper.INSTANCE.createdIndividualCustomerResponseFromIndividualCustomer(createdIndividualCustomer);
        return response;
    }


    @Override
    public UpdatedIndividualCustomerResponse update(UpdateIndividualCustomerRequest request) {
        IndividualCustomer existingCustomer = individualCustomerRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        IndividualCustomer updatedCustomer =
                IndividualCustomerMapper.INSTANCE.individualCustomerFromUpdateIndividualCustomerRequest(request, existingCustomer);
        updatedCustomer = individualCustomerRepository.save(updatedCustomer);
        UpdateCustomerEvent event = new UpdateCustomerEvent(
                updatedCustomer.getId(),
                updatedCustomer.getCustomerNumber(),
                updatedCustomer.getFirstName(),
                updatedCustomer.getLastName(),
                updatedCustomer.getNationalId(),
                updatedCustomer.getDateOfBirth().toString(),
                updatedCustomer.getMotherName(),
                updatedCustomer.getFatherName(),
                updatedCustomer.getGender()
        );

        updateCustomerProducer.produceCustomerUpdated(event);


        UpdatedIndividualCustomerResponse response =
                IndividualCustomerMapper.INSTANCE.updatedIndividualCustomerResponseFromIndividualCustomer(updatedCustomer);

        return response;
    }



    @Override
    public Customer findById(UUID id) {
        return individualCustomerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @Override
    public void delete(UUID id) {
        IndividualCustomer individualCustomer = individualCustomerRepository.findById(id).orElseThrow(() -> new RuntimeException("Individual Customer not found"));
        individualCustomerRepository.delete(individualCustomer);
        DeleteCustomerEvent event=new DeleteCustomerEvent(id);
        deleteCustomerProducer.produceCustomerDeleted(event);
    }



    @Override
    public CustomerResponse getById(UUID id) {
        return individualCustomerRepository.findById(id).stream().map(this::mapToResponse).findFirst().orElseThrow(()->new BusinessException("Customer Not Found"));
    }

    @Override
    public boolean existsByNationalId(String nationalId) {
       return individualCustomerRepository.existsByNationalId((nationalId));
    }

    private CustomerResponse mapToResponse(Customer customer){
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        return response;
    }

    @Override
    public FullCustomerResponse getFullCustomerById(UUID id) {
        IndividualCustomer customer = individualCustomerRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new BusinessException("Customer Not Found"));
        return mapToFullCustomerResponse(customer);
    }

    private FullCustomerResponse mapToFullCustomerResponse(IndividualCustomer ic) {
        FullCustomerResponse response = new FullCustomerResponse();

        response.setId(ic.getId());
        response.setCustomerNumber(ic.getCustomerNumber());
        response.setFirstName(ic.getFirstName());
        response.setLastName(ic.getLastName());
        response.setNationalId(ic.getNationalId());
        response.setDateOfBirth(ic.getDateOfBirth());
        response.setMotherName(ic.getMotherName());
        response.setFatherName(ic.getFatherName());
        response.setGender(ic.getGender());

        // Adresler
        List<AddressResponse> addressResponses = ic.getAddresses().stream()
                .map(address -> {
                    AddressResponse a = new AddressResponse();
                    a.setId(address.getId());
                    a.setStreet(address.getStreet());
                    a.setHouseNumber(address.getHouseNumber());
                    a.setDescription(address.getDescription());
                    a.setDefault(address.isDefault());
                    a.setDistrictId(address.getDistrict().getId());
                    a.setDistrictName(address.getDistrict().getName());
                    a.setCityId(address.getDistrict().getCity().getId());
                    a.setCityName(address.getDistrict().getCity().getName());
                    a.setCustomerId(ic.getId());
                    return a;
                }).toList();

        // Contact Mediums
        List<ContactMediumResponse> contactResponses = ic.getContactMediums().stream()
                .map(cm -> {
                    ContactMediumResponse c = new ContactMediumResponse();
                    c.setId(cm.getId());
                    c.setType(cm.getType().toString());
                    c.setValue(cm.getValue());
                    c.setPrimary(cm.isPrimary());
                    c.setCustomerId(ic.getId());
                    return c;
                }).toList();

        response.setAddresses(addressResponses);
        response.setContactMediums(contactResponses);
        return response;
    }


}
