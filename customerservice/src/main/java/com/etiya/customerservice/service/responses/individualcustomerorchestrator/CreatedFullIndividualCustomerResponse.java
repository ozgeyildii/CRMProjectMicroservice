package com.etiya.customerservice.service.responses.individualcustomerorchestrator;

import com.etiya.customerservice.service.responses.address.CreatedAddressResponse;
import com.etiya.customerservice.service.responses.contactmedium.CreatedContactMediumResponse;
import com.etiya.customerservice.service.responses.individualcustomers.CreatedIndividualCustomerResponse;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;

public class CreatedFullIndividualCustomerResponse {

    @JsonUnwrapped
    private CreatedIndividualCustomerResponse individualCustomer;

    private List<CreatedAddressResponse> addresses;

    private List<CreatedContactMediumResponse> contactMediums;

    public CreatedFullIndividualCustomerResponse(CreatedIndividualCustomerResponse individualCustomer, List<CreatedAddressResponse> addresses, List<CreatedContactMediumResponse> contactMediums) {
        this.individualCustomer = individualCustomer;
        this.addresses = addresses;
        this.contactMediums = contactMediums;
    }

    public CreatedIndividualCustomerResponse getIndividualCustomer() {
        return individualCustomer;
    }

    public void setIndividualCustomer(CreatedIndividualCustomerResponse individualCustomer) {
        this.individualCustomer = individualCustomer;
    }

    public List<CreatedAddressResponse> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<CreatedAddressResponse> addresses) {
        this.addresses = addresses;
    }

    public List<CreatedContactMediumResponse> getContactMediums() {
        return contactMediums;
    }

    public void setContactMediums(List<CreatedContactMediumResponse> contactMediums) {
        this.contactMediums = contactMediums;
    }


}
