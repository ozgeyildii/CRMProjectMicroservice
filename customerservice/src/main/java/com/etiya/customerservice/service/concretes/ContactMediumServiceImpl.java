package com.etiya.customerservice.service.concretes;

import com.etiya.common.events.contactmedium.CreateContactMediumEvent;
import com.etiya.common.events.contactmedium.DeleteContactMediumEvent;
import com.etiya.common.events.contactmedium.SoftDeleteContactMediumEvent;
import com.etiya.common.events.contactmedium.UpdateContactMediumEvent;
import com.etiya.customerservice.domain.entities.ContactMedium;
import com.etiya.customerservice.repository.ContactMediumRepository;
import com.etiya.customerservice.service.abstracts.CityService;
import com.etiya.customerservice.service.abstracts.ContactMediumService;
import com.etiya.customerservice.service.abstracts.CustomerService;
import com.etiya.customerservice.service.mappings.ContactMediumMapper;
import com.etiya.customerservice.service.requests.contactmedium.CreateContactMediumRequest;
import com.etiya.customerservice.service.requests.contactmedium.UpdateContactMediumRequest;
import com.etiya.customerservice.service.responses.contactmedium.CreatedContactMediumResponse;
import com.etiya.customerservice.service.responses.contactmedium.GetByIdContactMediumResponse;
import com.etiya.customerservice.service.responses.contactmedium.GetListContactMediumResponse;
import com.etiya.customerservice.service.responses.contactmedium.UpdatedContactMediumResponse;
import com.etiya.customerservice.service.rules.ContactMediumBusinessRules;
import com.etiya.customerservice.transport.kafka.producer.contactmedium.CreateContactMediumProducer;
import com.etiya.customerservice.transport.kafka.producer.contactmedium.DeleteContactMediumProducer;
import com.etiya.customerservice.transport.kafka.producer.contactmedium.SoftDeleteContactMediumProducer;
import com.etiya.customerservice.transport.kafka.producer.contactmedium.UpdateContactMediumProducer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContactMediumServiceImpl implements ContactMediumService {
    private final ContactMediumRepository contactMediumRepository;
    private final ContactMediumBusinessRules contactMediumBusinessRules;
    private final CustomerService customerService;
    private final CreateContactMediumProducer createContactMediumProducer;
    private final UpdateContactMediumProducer updateContactMediumProducer;
    private final DeleteContactMediumProducer deleteContactMediumProducer;
    private final SoftDeleteContactMediumProducer softDeleteContactMediumProducer;

    public ContactMediumServiceImpl(ContactMediumRepository contactMediumRepository, ContactMediumBusinessRules contactMediumBusinessRules, CustomerService customerService, CityService cityService, CreateContactMediumProducer createContactMediumProducer, UpdateContactMediumProducer updateContactMediumProducer, DeleteContactMediumProducer deleteContactMediumProducer, SoftDeleteContactMediumProducer softDeleteContactMediumProducer) {
        this.contactMediumRepository = contactMediumRepository;
        this.contactMediumBusinessRules = contactMediumBusinessRules;
        this.customerService = customerService;
        this.createContactMediumProducer = createContactMediumProducer;
        this.updateContactMediumProducer = updateContactMediumProducer;
        this.deleteContactMediumProducer = deleteContactMediumProducer;
        this.softDeleteContactMediumProducer = softDeleteContactMediumProducer;
    }

    @Override
    public CreatedContactMediumResponse add(CreateContactMediumRequest request) {
        ContactMedium contactMedium = ContactMediumMapper.INSTANCE.getContactMediumFromCreateContactMediumRequest(request);
        contactMediumBusinessRules.checkIsPrimaryOnlyOne(contactMedium);
        customerService.existsById(request.getCustomerId());
        ContactMedium created =  contactMediumRepository.save(contactMedium);
        CreateContactMediumEvent event = new CreateContactMediumEvent(
                created.getId(),
                created.getType().name(),
                created.getValue(),
                created.isPrimary(),
                created.getCustomer().getId()
        );
        createContactMediumProducer.produceContactMediumCreated(event);
        CreatedContactMediumResponse response = ContactMediumMapper.INSTANCE.getCreatedContactMediumResponseFromContactMedium(created);
        return response;
    }

    @Override
    public UpdatedContactMediumResponse update(UpdateContactMediumRequest request) {
        ContactMedium contactMediumOld = contactMediumRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Contact Medium not found"));

        ContactMedium contactMedium = ContactMediumMapper.INSTANCE.contactMediumFromUpdateContactMediumRequest(request, contactMediumOld);
        ContactMedium updated = contactMediumRepository.save(contactMedium);
        UpdateContactMediumEvent event = new UpdateContactMediumEvent(
                updated.getId(),
                updated.getType().name(),
                updated.getValue(),
                updated.isPrimary(),
                updated.getCustomer().getId()
        );
        updateContactMediumProducer.produceContactMediumUpdated(event);

        UpdatedContactMediumResponse response = ContactMediumMapper.INSTANCE.getUpdatedContactMediumResponseFromContactMedium(updated);

        return response;
    }


    @Override
    public List<GetListContactMediumResponse> getList() {
        List<ContactMedium> contactMediums = contactMediumRepository.findAll();

        List<GetListContactMediumResponse> responses = ContactMediumMapper.INSTANCE.getListContactMediumResponsesFromContactMedium(contactMediums);

        return responses;
    }

    @Override
    public void delete(int id) { //kalıcı silme- hard delete
        ContactMedium contactMedium = contactMediumRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        contactMediumBusinessRules.checkIsPrimary(contactMedium);
        contactMediumRepository.delete(contactMedium);
        DeleteContactMediumEvent event = new DeleteContactMediumEvent(id, contactMedium.getCustomer().getId());
        deleteContactMediumProducer.produceContactMediumDeleted(event);


    }

    @Override
    public void softDelete(int id) {
        ContactMedium contactMedium = contactMediumRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        contactMediumBusinessRules.checkIsPrimary(contactMedium);
        contactMedium.setDeletedDate(LocalDateTime.now());
        contactMediumRepository.save(contactMedium);
        SoftDeleteContactMediumEvent event = new SoftDeleteContactMediumEvent(id, contactMedium.getCustomer().getId(),contactMedium.getDeletedDate().toString());
        softDeleteContactMediumProducer.produceAddressSoftDeleted(event);
    }

    @Override
    public GetByIdContactMediumResponse getById(int id) {
        ContactMedium contactMedium = contactMediumRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        GetByIdContactMediumResponse response = ContactMediumMapper.INSTANCE.getContactMediumResponseFromContactMedium(contactMedium);
        return response;
    }

    @Override
    public GetByIdContactMediumResponse getByValue(String value) {
        ContactMedium contactMedium =
                contactMediumRepository.findByValue(value);
        GetByIdContactMediumResponse response =
                ContactMediumMapper.INSTANCE.getContactMediumResponseFromContactMedium(contactMedium);
        return response;
    }

    @Override
    public List<GetListContactMediumResponse> getListByType(String type) {
        List<ContactMedium> contactMediums = contactMediumRepository.findByType(type);
        List<GetListContactMediumResponse> responses =
                ContactMediumMapper.INSTANCE.getListContactMediumResponsesFromContactMedium(contactMediums);
        return responses;
    }

    @Override
    public List<GetListContactMediumResponse> getListByCustomerId(int id) {
        ContactMedium contactMedium= contactMediumRepository.findById(id).orElseThrow(()->new RuntimeException("Contact medium does not exist"));
        List<ContactMedium> contactMediums=contactMediumRepository.findByCustomerId(contactMedium.getCustomer().getId());
        List<GetListContactMediumResponse> responses = ContactMediumMapper.INSTANCE.getListContactMediumResponsesFromContactMedium(contactMediums);
        return responses;
    }


}
