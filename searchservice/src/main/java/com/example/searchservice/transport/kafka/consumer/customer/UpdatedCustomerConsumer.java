package com.example.searchservice.transport.kafka.consumer.customer;

import com.etiya.common.events.customer.UpdateCustomerEvent;
import com.example.searchservice.domain.CustomerSearch;
import com.example.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
    public class UpdatedCustomerConsumer {
        private final CustomerSearchService customerSearchService;
        private final Logger LOGGER = LoggerFactory.getLogger(UpdatedCustomerConsumer.class);

        public UpdatedCustomerConsumer(CustomerSearchService customerSearchService) {
            this.customerSearchService = customerSearchService;
        }

        @Bean
        public Consumer <UpdateCustomerEvent> customerUpdated() {
            return event -> {
                CustomerSearch customerSearch = new CustomerSearch(event.customerId().toString(),
                        event.customerNumber(),
                        event.firstName(),
                        event.lastName(),event.nationalId(),event.dateOfBirth(), event.motherName(), event.fatherName(), event.gender());
                customerSearchService.updateCustomer(customerSearch);
            };
        }
    }


