package com.example.searchservice.transport.kafka.address;

import com.etiya.common.events.address.UpdateAddressEvent;
import com.example.searchservice.domain.Address;
import com.example.searchservice.service.CustomerSearchService;
import com.example.searchservice.transport.kafka.customer.consumer.CreatedCustomerConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UpdatedAddressConsumer {
    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(UpdatedAddressConsumer.class);

    public UpdatedAddressConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @KafkaListener(topics = "update-address", groupId = "update-address-group")
    public void consume(UpdateAddressEvent event) {
        LOGGER.info(String.format("Consumed Address => %s", event.id()));
        Address address = new Address(
                event.id(),
                event.houseNumber(),
                event.description(),
                event.street(),
                event.isDefault(),
                event.districtId()
        );
        customerSearchService.updateAddress(address, event.customerId());

    }
}
