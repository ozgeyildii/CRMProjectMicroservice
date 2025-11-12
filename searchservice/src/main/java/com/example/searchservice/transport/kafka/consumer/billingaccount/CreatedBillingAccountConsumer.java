package com.example.searchservice.transport.kafka.consumer.billingaccount;

import com.etiya.common.events.billingaccount.CreateBillingAccountEvent;
import com.example.searchservice.domain.BillingAccount;
import com.example.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class CreatedBillingAccountConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(CreatedBillingAccountConsumer.class);

    public CreatedBillingAccountConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<CreateBillingAccountEvent> billingAccountCreated() {
        return event -> {
            BillingAccount billingAccount = new BillingAccount(
                    event.id(),
                    event.type(),
                    event.status(),
                    event.accountNumber(),
                    event.accountName(),
                    event.customerId(),
                    event.addressId()
            );

            customerSearchService.addBillingAccount(billingAccount);
            LOGGER.info(String.format("Consumed Billing account (created)=> %s", event));
        };
    }
}