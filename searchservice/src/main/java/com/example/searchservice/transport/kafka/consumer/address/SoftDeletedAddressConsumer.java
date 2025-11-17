package com.example.searchservice.transport.kafka.consumer.address;

import com.etiya.common.events.address.DeleteAddressEvent;
import com.etiya.common.events.address.SoftDeleteAddressEvent;
import com.example.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class SoftDeletedAddressConsumer {

    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(SoftDeletedAddressConsumer.class);

    public SoftDeletedAddressConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<SoftDeleteAddressEvent> addressSoftDeleted() {
        return event -> {
            customerSearchService.softDeleteAddress(event.id(), event.customerId(),event.deletedDate());
            LOGGER.info(String.format("Consumed Address (soft-deleted)=> %s", event.id()));
        };
    }
}
