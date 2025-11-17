package com.example.searchservice.transport.kafka.consumer.createdevents;

import com.etiya.common.events.address.CreateAddressEvent;
import com.etiya.common.events.contactmedium.CreateContactMediumEvent;
import com.etiya.common.events.customer.CreateCustomerEvent;
import com.example.searchservice.domain.Address;
import com.example.searchservice.domain.ContactMedium;
import com.example.searchservice.domain.CustomerSearch;
import com.example.searchservice.service.CustomerSearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;
import java.util.Map;
import java.util.function.Consumer;

@Configuration
public class CreatedEventsDispatcher {

    private final CustomerSearchService customerSearchService;
    private final ObjectMapper mapper = new ObjectMapper();

    public CreatedEventsDispatcher(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @Bean
    public Consumer<String> createdEvents() {
        Map<String, Consumer<String>> handlers = Map.of(
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

        return base64Payload -> {
            var json = decode(base64Payload);   // ★★★ EN KRİTİK SATIR
            var map = read(json, Map.class);
            String type = (String) map.get("eventType");
            handlers.get(type).accept(json);
        };
    }

    private String decode(String base64) {
        return new String(Base64.getDecoder().decode(base64));
    }

    private <T> T read(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("JSON parsing error for: " + clazz.getSimpleName(), e);
        }
    }
}