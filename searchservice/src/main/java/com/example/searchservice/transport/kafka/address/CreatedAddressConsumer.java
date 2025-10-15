package com.example.searchservice.transport.kafka.address;

import com.etiya.common.events.CreateAddressEvent;
import com.etiya.common.events.CreateCustomerEvent;
import com.example.searchservice.domain.Address;
import com.example.searchservice.domain.CustomerSearch;
import com.example.searchservice.service.CustomerSearchService;
import com.example.searchservice.transport.kafka.customer.consumer.CreatedCustomerConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


    @Service
    public class CreatedAddressConsumer {

        private final CustomerSearchService customerSearchService;
        private final Logger LOGGER = LoggerFactory.getLogger(CreatedCustomerConsumer.class);

        public CreatedAddressConsumer(CustomerSearchService customerSearchService) {
            this.customerSearchService = customerSearchService;
        }

        @KafkaListener(topics = "create-address", groupId = "create-address-group")
        public void consume(CreateAddressEvent event) {
            LOGGER.info(String.format("Consumed Customer => %s", event.id()));
            Address address = new Address(
                    event.id(),
                    event.houseNumber(),
                    event.description(),
                    event.street(),
                    event.isDefault(),
                    event.districtId(),
                    event.customerId()
            );
            customerSearchService.addAddress(address);

        }
    }
