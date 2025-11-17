package com.etiya.customerservice.service.requests.individualcustomerorchestrator;

import com.etiya.customerservice.service.requests.address.CreateAddressRequest;
import com.etiya.customerservice.service.requests.individualcustomers.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.requests.contactmedium.CreateContactMediumRequest;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CreateFullIndividualCustomerRequest {

    @JsonUnwrapped
    @NotNull
    private CreateIndividualCustomerRequest individualCustomer;

    @NotEmpty
    private List<CreateAddressRequest> addresses;

    @NotEmpty
    private List<CreateContactMediumRequest> contactMediums;

    public CreateIndividualCustomerRequest getIndividualCustomer() { return individualCustomer; }
    public void setIndividualCustomer(CreateIndividualCustomerRequest individualCustomer) { this.individualCustomer = individualCustomer; }

    public List<CreateAddressRequest> getAddresses() { return addresses; }
    public void setAddresses(List<CreateAddressRequest> addresses) { this.addresses = addresses; }

    public List<CreateContactMediumRequest> getContactMediums() { return contactMediums; }
    public void setContactMediums(List<CreateContactMediumRequest> contactMediums) { this.contactMediums = contactMediums; }
}
