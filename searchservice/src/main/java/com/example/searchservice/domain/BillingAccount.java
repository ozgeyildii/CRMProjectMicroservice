package com.example.searchservice.domain;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class BillingAccount {
    private int id;
    private String type;
    private String status;
    @Field(type = FieldType.Keyword)
    private String accountNumber;
    private String accountName;
    private String customerId;
    private int addressId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public BillingAccount() {
    }

    public BillingAccount(int id, String type, String status, String accountNumber, String accountName, String customerId, int addressId) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.customerId = customerId;
        this.addressId = addressId;
    }
}
