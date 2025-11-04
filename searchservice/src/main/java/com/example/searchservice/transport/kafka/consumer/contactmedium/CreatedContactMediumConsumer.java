package com.example.searchservice.transport.kafka.consumer.contactmedium;

import com.etiya.common.events.contactmedium.CreateContactMediumEvent;
import com.example.searchservice.domain.ContactMedium;
import com.example.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

//@Service
@Configuration
public class CreatedContactMediumConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(CreatedContactMediumConsumer.class);

    public CreatedContactMediumConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<CreateContactMediumEvent> contactCreated() {
        return event -> {
            ContactMedium contactMedium = new ContactMedium(
                    event.id(),
                    event.type(),
                    event.value(),
                    event.isPrimary(),
                    event.customerId().toString()
            );
            customerSearchService.addContactMedium(contactMedium);
            LOGGER.info(String.format("Consumed Contact Medium (created)=> %s", event));
        };
    }
   /* @KafkaListener(topics = "create-contactmedium", groupId = "create-contactmedium-group")
    public void consume(CreateContactMediumEvent event) {
        LOGGER.info(String.format("Consumed Contact Medium => %s", event.id()));
        ContactMedium contactMedium = new ContactMedium(
                event.id(),
                event.type(),
                event.value(),
                event.isPrimary()
        );
        customerSearchService.addContactMedium(contactMedium, event.customerId());

    }*/
}