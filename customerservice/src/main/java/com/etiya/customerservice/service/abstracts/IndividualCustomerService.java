package com.etiya.customerservice.service.abstracts;

import com.etiya.customerservice.domain.entities.Customer;
import com.etiya.customerservice.service.requests.individualcustomers.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.individualcustomers.CreatedIndividualCustomerResponse;

import java.util.UUID;

public interface IndividualCustomerService {

    CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest request);

    Customer findById(UUID id);
}
