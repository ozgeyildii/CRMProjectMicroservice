package com.etiya.customerservice.service.abstracts;

import com.etiya.common.responses.CustomerResponse;
import com.etiya.customerservice.domain.entities.Customer;
import com.etiya.customerservice.service.requests.individualcustomers.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.requests.individualcustomers.UpdateIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.individualcustomers.CreatedIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomers.UpdatedIndividualCustomerResponse;

import java.util.UUID;

public interface IndividualCustomerService {

    CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest request);
    UpdatedIndividualCustomerResponse update(UpdateIndividualCustomerRequest request);
    Customer findById(UUID id);
    void delete(UUID id);
    CustomerResponse getById(UUID id);
}
