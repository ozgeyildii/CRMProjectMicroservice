package com.etiya.customerservice.service.requests.corporateCustomer;

import java.util.UUID;

public class UpdateCorporateCustomerRequest {
    private UUID id;
    private String companyName;
    private String taxNumber;
    private String companyType;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UpdateCorporateCustomerRequest(UUID id, String companyName, String taxNumber, String companyType) {
        this.id=id;
        this.companyName = companyName;
        this.taxNumber = taxNumber;
        this.companyType = companyType;
    }

    public UpdateCorporateCustomerRequest() {
    }
}
