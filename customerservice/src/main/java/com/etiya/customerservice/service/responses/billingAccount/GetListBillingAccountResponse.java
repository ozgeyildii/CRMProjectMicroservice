package com.etiya.customerservice.service.responses.billingAccount;

import com.etiya.customerservice.domain.enums.BillingAccountStatus;
import com.etiya.customerservice.domain.enums.BillingAccountType;

public class GetListBillingAccountResponse {
    private int id;
    private BillingAccountType type;
    private String accountName;
    private String accountNumber;
    private BillingAccountStatus status;
    private int customerId;
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

    public BillingAccountStatus getStatus() {
        return status;
    }

    public void setStatus(BillingAccountStatus status) {
        this.status = status;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public GetListBillingAccountResponse(int id, BillingAccountType type, String accountName, String accountNumber, BillingAccountStatus status, int customerId, int addressId) {
        this.id = id;
        this.type = type;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.status = status;
        this.customerId = customerId;
        this.addressId = addressId;
    }

    public GetListBillingAccountResponse() {
    }
}
