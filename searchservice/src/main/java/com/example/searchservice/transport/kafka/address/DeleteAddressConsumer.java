package com.example.searchservice.transport.kafka.address;

import com.etiya.common.events.CreateAddressEvent;
import com.etiya.common.events.DeleteAddressEvent;
import com.example.searchservice.domain.Address;
import com.example.searchservice.service.CustomerSearchService;
import com.example.searchservice.transport.kafka.customer.consumer.CreatedCustomerConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DeleteAddressConsumer {
    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(CreatedCustomerConsumer.class);

    public DeleteAddressConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @KafkaListener(topics = "delete-address", groupId = "delete-address-group")
    public void consume(DeleteAddressEvent event) {
        LOGGER.info(String.format("Consumed Customer => %s", event.id()));
        customerSearchService.deleteAddress(event.id(),event.customerId());

    }
}
