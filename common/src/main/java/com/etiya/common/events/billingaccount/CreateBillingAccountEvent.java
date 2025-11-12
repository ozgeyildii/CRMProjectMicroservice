package com.etiya.common.events.billingaccount;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateBillingAccountEvent(
        int id,
        String type,
        String status,
        String accountNumber,
        String accountName,
        String customerId,
        int addressId
) {}

