package com.example.searchservice.transport.kafka.consumer.address;

import com.etiya.common.events.address.DeleteAddressEvent;
import com.example.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class DeletedAddressConsumer {
private final CustomerSearchService customerSearchService;
private final Logger LOGGER = LoggerFactory.getLogger(DeletedAddressConsumer.class);

public DeletedAddressConsumer(CustomerSearchService customerSearchService) {
    this.customerSearchService = customerSearchService;
}

@Bean
public Consumer<DeleteAddressEvent> addressDeleted() {
   return event->{
    customerSearchService.deleteAddress(event.id(), event.customerId());
    LOGGER.info(String.format("Consumed Address (deleted) => %s", event.id()));
};
}


   /* @KafkaListener(topics = "delete-address", groupId = "delete-address-group")
    public void consume(DeleteAddressEvent event) {
        LOGGER.info(String.format("Consumed Address => %s", event.id()));
        customerSearchService.deleteAddress(event.id(),event.customerId());

    }*/
}
