package com.example.searchservice.transport.kafka.consumer.customer;

import com.etiya.common.events.customer.DeleteCustomerEvent;
import com.example.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

public class DeletedCustomerConsumer {
    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(DeletedCustomerConsumer.class);

    public DeletedCustomerConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<DeleteCustomerEvent> customerDeleted() {
        return event -> {
            customerSearchService.deleteCustomer(event.customerId());
            LOGGER.info(String.format("Consumed Customer Medium (deleted) => %s"));
        };

    }
}
