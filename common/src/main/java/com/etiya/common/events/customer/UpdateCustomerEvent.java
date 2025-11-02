package com.etiya.common.events.customer;

import java.util.UUID;

public record UpdateCustomerEvent(UUID customerId, String customerNumber, String firstName, String lastName, String nationalId,
                                  String dateOfBirth, String motherName, String fatherName, String gender) {
}
