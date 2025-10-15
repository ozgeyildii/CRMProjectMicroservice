package com.etiya.customerservice.service.responses.corporateCustomer;

import java.util.UUID;

public class CreatedCorporateCustomerResponse {
    private UUID id;
    private String companyName;
    private String taxNumber;
    private String companyType;
    private String customerNumber;

    public CreatedCorporateCustomerResponse() {
    }

    public CreatedCorporateCustomerResponse(UUID id, String companyName, String taxNumber, String companyType, String customerNumber) {
        this.id = id;
        this.companyName = companyName;
        this.taxNumber = taxNumber;
        this.companyType = companyType;
        this.customerNumber = customerNumber;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}