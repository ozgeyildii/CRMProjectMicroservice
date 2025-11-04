package com.example.searchservice.transport.kafka.consumer.address;

import com.etiya.common.events.address.UpdateAddressEvent;
import com.example.searchservice.domain.Address;
import com.example.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class UpdatedAddressConsumer {
    private final CustomerSearchService customerSearchService;
    private final Logger LOGGER = LoggerFactory.getLogger(UpdatedAddressConsumer.class);

    public UpdatedAddressConsumer(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<UpdateAddressEvent> addressUpdated() {
        return event -> {
            Address address = new Address(
                    event.id(),
                    event.street(),
                    event.houseNumber(),
                    event.description(),
                    event.isDefault(),
                    event.districtId(),
                    event.districtName(),
                    event.cityId(),
                    event.cityName(),
                    event.customerId().toString()
            );
            customerSearchService.updateAddress(address);
            LOGGER.info(String.format("Consumed Address (updated) => %s", event.id()));
        };
    }



   /* @KafkaListener(topics = "update-address", groupId = "update-address-group")
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

    }*/
}
