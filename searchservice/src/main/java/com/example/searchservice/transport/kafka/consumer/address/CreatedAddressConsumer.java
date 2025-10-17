package com.example.searchservice.transport.kafka.consumer.address;

import com.etiya.common.events.address.CreateAddressEvent;
import com.example.searchservice.domain.Address;
import com.example.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;


@Configuration
    public class CreatedAddressConsumer {

        private final CustomerSearchService customerSearchService;
        private final Logger LOGGER = LoggerFactory.getLogger(CreatedAddressConsumer.class);

        public CreatedAddressConsumer(CustomerSearchService customerSearchService) {
            this.customerSearchService = customerSearchService;
        }
       @Bean
        public Consumer<CreateAddressEvent> addressCreated(){
            return event->{
            Address address = new Address(
                    event.id(),
                    event.street(),
                    event.houseNumber(),
                    event.description(),
                    event.isDefault(),
                    event.districtId(),
                    event.customerId()
            );

            customerSearchService.addAddress(address);
            LOGGER.info(String.format("Consumed Address (created)=> %s", event.id()));
            };
        }
       /* @KafkaListener(topics = "create-address", groupId = "create-address-group")
        public void consume(CreateAddressEvent event) {
            LOGGER.info(String.format("Consumed Address => %s", event.id()));
            Address address = new Address(
                    event.id(),
                    event.houseNumber(),
                    event.description(),
                    event.street(),
                    event.isDefault(),
                    event.districtId()
            );
            customerSearchService.addAddress(address, event.customerId());

        }*/
    }
