package com.etiya.common.events.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;
@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateCustomerEvent(UUID customerId, String customerNumber, String firstName, String lastName, String nationalId,
                                  String dateOfBirth, String motherName, String fatherName, String gender) {
}
