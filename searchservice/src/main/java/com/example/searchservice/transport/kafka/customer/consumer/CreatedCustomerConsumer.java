package com.example.searchservice.transport.kafka.customer.consumer;

import com.etiya.common.events.CreateCustomerEvent;
import com.example.searchservice.domain.CustomerSearch;
import com.example.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CreatedCustomerConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(CreatedCustomerConsumer.class);

    public CreatedCustomerConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @KafkaListener(topics = "create-customer",groupId = "create-customer-group")
    public void consume(CreateCustomerEvent event){
        LOGGER.info(String.format("Consumed Customer => %s",event.customerId()));
        CustomerSearch customerSearch = new CustomerSearch(event.customerId(),
                event.customerNumber(),
                event.firstName(),
                event.lastName(),event.nationalId(),event.dateOfBirth(), event.motherName(), event.fatherName(), event.gender());
        customerSearchService.add(customerSearch);
    }
}