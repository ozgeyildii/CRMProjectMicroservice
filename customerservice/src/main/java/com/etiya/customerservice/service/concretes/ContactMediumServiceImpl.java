package com.etiya.customerservice.service.concretes;


import com.etiya.common.events.contactmedium.CreateContactMediumEvent;
import com.etiya.common.events.contactmedium.DeleteContactMediumEvent;
import com.etiya.common.events.contactmedium.SoftDeleteContactMediumEvent;
import com.etiya.common.events.contactmedium.UpdateContactMediumEvent;
import com.etiya.customerservice.domain.entities.ContactMedium;
import com.etiya.customerservice.domain.entities.Customer;

import com.etiya.customerservice.infrastructure.OutboxService;
import com.etiya.customerservice.repository.ContactMediumRepository;
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
import com.etiya.customerservice.transport.kafka.producer.contactmedium.DeleteContactMediumProducer;
import com.etiya.customerservice.transport.kafka.producer.contactmedium.SoftDeleteContactMediumProducer;
import com.etiya.customerservice.transport.kafka.producer.contactmedium.UpdateContactMediumProducer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactMediumServiceImpl implements ContactMediumService {
    private final ContactMediumRepository contactMediumRepository;
    private final ContactMediumBusinessRules contactMediumBusinessRules;
    private final CustomerService customerService;
    private final OutboxService outboxService;
    private final UpdateContactMediumProducer updateContactMediumProducer;
    private final DeleteContactMediumProducer deleteContactMediumProducer;
    private final SoftDeleteContactMediumProducer softDeleteContactMediumProducer;

    public ContactMediumServiceImpl(ContactMediumRepository contactMediumRepository, ContactMediumBusinessRules contactMediumBusinessRules, CustomerService customerService, OutboxService outboxService, UpdateContactMediumProducer updateContactMediumProducer, DeleteContactMediumProducer deleteContactMediumProducer, SoftDeleteContactMediumProducer softDeleteContactMediumProducer) {
        this.contactMediumRepository = contactMediumRepository;
        this.contactMediumBusinessRules = contactMediumBusinessRules;
        this.customerService = customerService;
        this.outboxService = outboxService;
        this.updateContactMediumProducer = updateContactMediumProducer;
        this.deleteContactMediumProducer = deleteContactMediumProducer;
        this.softDeleteContactMediumProducer = softDeleteContactMediumProducer;
    }

    @Override
    public CreatedContactMediumResponse add(CreateContactMediumRequest request) {
        ContactMedium contactMedium = ContactMediumMapper.INSTANCE.getContactMediumFromCreateContactMediumRequest(request);
        Customer customer = customerService.getByEntityId(request.getCustomerId());
        contactMedium.setCustomer(customer);
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

        outboxService.save(event, "CONTACT_MEDIUM", created.getCustomer().getId().toString());

        CreatedContactMediumResponse response = ContactMediumMapper.INSTANCE.getCreatedContactMediumResponseFromContactMedium(created);
        return response;
    }

    @Override
    public UpdatedContactMediumResponse update(UpdateContactMediumRequest request) {
        ContactMedium contactMediumOld = contactMediumRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Contact Medium not found"));

        ContactMedium contactMedium = ContactMediumMapper.INSTANCE.contactMediumFromUpdateContactMediumRequest(request, contactMediumOld);
        ContactMedium updated = contactMediumRepository.save(contactMedium);
        UpdateContactMediumEvent event= new UpdateContactMediumEvent(
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
    @Transactional
    public List<UpdatedContactMediumResponse> updateMultiple(List<UpdateContactMediumRequest> requests) {
        List<UpdatedContactMediumResponse> responses = new ArrayList<>();

        for (UpdateContactMediumRequest request : requests) {
            ContactMedium existing = contactMediumRepository.findById(request.getId())
                    .orElseThrow(() -> new RuntimeException("Contact Medium not found"));

            ContactMedium updatedEntity = ContactMediumMapper.INSTANCE
                    .contactMediumFromUpdateContactMediumRequest(request, existing);

            ContactMedium updated = contactMediumRepository.save(updatedEntity);

            // Kafka event
            UpdateContactMediumEvent event = new UpdateContactMediumEvent(
                    updated.getId(),
                    updated.getType().name(),
                    updated.getValue(),
                    updated.isPrimary(),
                    updated.getCustomer().getId()
            );
            updateContactMediumProducer.produceContactMediumUpdated(event);

            UpdatedContactMediumResponse response = ContactMediumMapper.INSTANCE
                    .getUpdatedContactMediumResponseFromContactMedium(updated);

            responses.add(response);
        }

        return responses;
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
/*
    @Override
    public void addForCustomer(UUID customerId, CreateContactMediumRequest request) {
        ContactMedium cm = new ContactMedium();
        cm.setType(ContactMediumType.valueOf(request.getType().toUpperCase()));
        cm.setValue(request.getValue());
        cm.setPrimary(request.isPrimary());
        cm.setCustomer(individualCustomerService.findById(customerId));
        contactMediumRepository.save(cm);
        CreateContactMediumEvent event = new CreateContactMediumEvent(
                cm.getId(),
                cm.getType().name(),
                cm.getValue(),
                cm.isPrimary(),
                cm.getCustomer().getId()
        );
        createContactMediumProducer.produceContactMediumCreated(event);
    }
*/
}
