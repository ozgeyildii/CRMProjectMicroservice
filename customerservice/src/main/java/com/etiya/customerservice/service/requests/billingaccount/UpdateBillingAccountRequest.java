package com.etiya.customerservice.service.requests.billingaccount;


import com.etiya.customerservice.domain.enums.BillingAccountStatus;
import com.etiya.customerservice.domain.enums.BillingAccountType;

import java.util.UUID;

public class UpdateBillingAccountRequest {
    private int id;
    private String accountName;
    private UUID customerId;
    private int addressId;
    private BillingAccountType type;
    private BillingAccountStatus status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public BillingAccountType getType() {
        return type;
    }

    public void setType(BillingAccountType type) {
        this.type = type;
    }

    public BillingAccountStatus getStatus() {
        return status;
    }

    public void setStatus(BillingAccountStatus status) {
        this.status = status;
    }

    public UpdateBillingAccountRequest(int id, String accountName, UUID customerId, int addressId, BillingAccountType type, BillingAccountStatus status) {
        this.id = id;
        this.accountName = accountName;
        this.customerId = customerId;
        this.addressId = addressId;
        this.type = type;
        this.status = status;
    }

    public UpdateBillingAccountRequest() {
    }
}
