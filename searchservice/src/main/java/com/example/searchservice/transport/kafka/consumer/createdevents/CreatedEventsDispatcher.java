package com.example.searchservice.transport.kafka.consumer.createdevents;

import com.etiya.common.events.address.CreateAddressEvent;
import com.etiya.common.events.contactmedium.CreateContactMediumEvent;
import com.etiya.common.events.customer.CreateCustomerEvent;
import com.example.searchservice.domain.Address;
import com.example.searchservice.domain.ContactMedium;
import com.example.searchservice.domain.CustomerSearch;
import com.example.searchservice.service.CustomerSearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Consumer;

@Configuration
public class CreatedEventsDispatcher {

    private static final Logger log = LoggerFactory.getLogger(CreatedEventsDispatcher.class);
    private final CustomerSearchService customerSearchService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CreatedEventsDispatcher(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<byte[]> createdEvents() {
        Map<String, RunnableWithPayload> handlers = Map.of(
                CreateCustomerEvent.class.getSimpleName(), json -> {
                    var e = read(json, CreateCustomerEvent.class);
                    customerSearchService.add(new CustomerSearch(
                            e.customerId().toString(),
                            e.customerNumber(),
                            e.firstName(),
                            e.lastName(),
                            e.nationalId(),
                            e.dateOfBirth(),
                            e.motherName(),
                            e.fatherName(),
                            e.gender()
                    ));
                },

                CreateAddressEvent.class.getSimpleName(), json -> {
                    var e = read(json, CreateAddressEvent.class);
                    customerSearchService.addAddress(new Address(
                            e.id(), e.street(), e.houseNumber(), e.description(),
                            e.isDefault(), e.districtId(), e.districtName(),
                            e.cityId(), e.cityName(), e.customerId().toString()
                    ));
                },

                CreateContactMediumEvent.class.getSimpleName(), json -> {
                    var e = read(json, CreateContactMediumEvent.class);
                    customerSearchService.addContactMedium(new ContactMedium(
                            e.id(), e.type(), e.value(), e.isPrimary(), e.customerId().toString()
                    ));
                }
        );

        // 💡 Consumer burada kapanmalı
        return payload -> {
            String json = new String(payload, StandardCharsets.UTF_8);
            try {
                var map = objectMapper.readValue(json, Map.class);
                String type = (String) map.get("eventType");
                var handler = handlers.get(type);
                if (handler != null) handler.run(json);
                else log.warn("⚠️ Unknown eventType: {}", type);
            } catch (Exception e) {
                log.error("❌ Failed to parse event payload: {}", e.getMessage(), e);
            }
        };
    }

    private <T> T read(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new IllegalStateException("❌ JSON parse failed for " + clazz.getSimpleName(), e);
        }
    }

    @FunctionalInterface
    interface RunnableWithPayload {
        void run(String payload);
    }
}
