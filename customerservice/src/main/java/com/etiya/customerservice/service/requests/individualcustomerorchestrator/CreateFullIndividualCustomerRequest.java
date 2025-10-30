package com.etiya.customerservice.service.requests.individualcustomerorchestrator;

import com.etiya.customerservice.service.requests.individualcustomers.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.requests.addressorchestratorrequest.CreateFullAddressRequest;
import com.etiya.customerservice.service.requests.contactmedium.CreateContactMediumRequest;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;

public class CreateFullIndividualCustomerRequest {

    @JsonUnwrapped
    private CreateIndividualCustomerRequest individualCustomer;

    private List<CreateFullAddressRequest> addresses;

    private List<CreateContactMediumRequest> contactMediums;

    public CreateIndividualCustomerRequest getIndividualCustomer() { return individualCustomer; }
    public void setIndividualCustomer(CreateIndividualCustomerRequest individualCustomer) { this.individualCustomer = individualCustomer; }

    public List<CreateFullAddressRequest> getAddresses() { return addresses; }
    public void setAddresses(List<CreateFullAddressRequest> addresses) { this.addresses = addresses; }

    public List<CreateContactMediumRequest> getContactMediums() { return contactMediums; }
    public void setContactMediums(List<CreateContactMediumRequest> contactMediums) { this.contactMediums = contactMediums; }
}
