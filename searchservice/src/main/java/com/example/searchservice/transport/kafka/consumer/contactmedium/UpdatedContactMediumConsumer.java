package com.example.searchservice.transport.kafka.consumer.contactmedium;

import com.etiya.common.events.contactmedium.UpdateContactMediumEvent;
import com.example.searchservice.domain.ContactMedium;
import com.example.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class UpdatedContactMediumConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(UpdatedContactMediumConsumer.class);

    public UpdatedContactMediumConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<UpdateContactMediumEvent> contactUpdated() {
        return event->{ContactMedium contactMedium = new ContactMedium(
                event.id(),
                event.type(),
                event.value(),
                event.isPrimary(),
                event.customerId()
        );
        LOGGER.info(String.format("Consumed Contact Medium (updated) => %s", event.id()));
        customerSearchService.updateContactMedium(contactMedium);
        };}
/*
    @KafkaListener(topics = "update-contactmedium", groupId = "update-contactmedium-group")
    public void consume(UpdateContactMediumEvent event) {
        LOGGER.info(String.format("Consumed Contact Medium => %s", event.id()));
        ContactMedium contactMedium = new ContactMedium(
                event.id(),
                event.type(),
                event.value(),
                event.isPrimary()
        );
        customerSearchService.updateContactMedium(contactMedium, event.customerId());

    }*/
}