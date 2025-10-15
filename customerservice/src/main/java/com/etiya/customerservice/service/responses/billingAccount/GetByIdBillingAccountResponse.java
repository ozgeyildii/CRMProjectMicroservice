package com.etiya.customerservice.service.responses.billingAccount;


import com.etiya.customerservice.domain.enums.BillingAccountStatus;
import com.etiya.customerservice.domain.enums.BillingAccountType;

import java.util.UUID;

public class GetByIdBillingAccountResponse {
    private int id;
    private BillingAccountType type;
    private BillingAccountStatus status;
    private String accountName;
    private String accountNumber;
    private UUID customerId;
    private int addressId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public GetByIdBillingAccountResponse(int id, BillingAccountType type, BillingAccountStatus status, String accountName, String accountNumber, UUID customerId, int addressId) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.addressId = addressId;
    }

    public GetByIdBillingAccountResponse() {
    }
}
