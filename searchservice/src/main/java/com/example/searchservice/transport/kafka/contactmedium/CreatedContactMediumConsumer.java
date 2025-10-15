package com.example.searchservice.transport.kafka.contactmedium;

import com.etiya.common.events.contactmedium.CreateContactMediumEvent;
import com.example.searchservice.domain.ContactMedium;
import com.example.searchservice.service.CustomerSearchService;
import com.example.searchservice.transport.kafka.customer.consumer.CreatedCustomerConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CreatedContactMediumConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(CreatedContactMediumConsumer.class);

    public CreatedContactMediumConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @KafkaListener(topics = "create-contactmedium", groupId = "create-contactmedium-group")
    public void consume(CreateContactMediumEvent event) {
        LOGGER.info(String.format("Consumed Contact Medium => %s", event.id()));
        ContactMedium contactMedium = new ContactMedium(
                event.id(),
                event.type(),
                event.value(),
                event.isPrimary()
        );
        customerSearchService.addContactMedium(contactMedium, event.customerId());

    }
}