package com.example.searchservice.transport.kafka.contactmedium;

import com.etiya.common.events.contactmedium.DeleteContactMediumEvent;
import com.example.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DeleteContactMediumConsumer {
    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(DeleteContactMediumConsumer.class);

    public DeleteContactMediumConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @KafkaListener(topics = "delete-contactmedium", groupId = "delete-contactmedium-group")
    public void consume(DeleteContactMediumEvent event) {
        LOGGER.info(String.format("Consumed Contact Medium => %s", event.id()));
        customerSearchService.deleteContactMedium(event.id(),event.customerId());

    }
}
