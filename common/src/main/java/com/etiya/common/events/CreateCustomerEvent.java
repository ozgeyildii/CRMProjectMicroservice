package com.etiya.common.events;

import java.time.LocalDateTime;

public record CreateCustomerEvent(String customerId, String customerNumber, String firstName, String lastName, String nationalId,
                                  String dateOfBirth, String motherName, String fatherName, String gender) {
}
