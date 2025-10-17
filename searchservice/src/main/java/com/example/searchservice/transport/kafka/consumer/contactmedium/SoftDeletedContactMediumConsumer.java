package com.example.searchservice.transport.kafka.consumer.contactmedium;

import com.etiya.common.events.contactmedium.SoftDeleteContactMediumEvent;
import com.example.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class SoftDeletedContactMediumConsumer {
    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(SoftDeletedContactMediumConsumer.class);

    public SoftDeletedContactMediumConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<SoftDeleteContactMediumEvent> contactSoftDeleted() {
        return event -> {
            customerSearchService.softDeleteContactMedium(event.id(), event.customerId(), event.deletedDate());
            LOGGER.info(String.format("Consumed Contact Medium (soft-deleted) => %s", event.id()));
        };

    }
   /* @KafkaListener(topics = "delete-contactmedium", groupId = "delete-contactmedium-group")
    public void consume(DeleteContactMediumEvent event) {
        LOGGER.info(String.format("Consumed Contact Medium => %s", event.id()));
        customerSearchService.deleteContactMedium(event.id(),event.customerId());

    }*/
}
