package com.etiya.customerservice.service.abstracts;


import com.etiya.customerservice.service.requests.individualcustomerorchestrator.CreateFullIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.individualcustomers.CreatedIndividualCustomerResponse;

public interface CustomerOrchestratorService {

    CreatedIndividualCustomerResponse createFullCustomer(CreateFullIndividualCustomerRequest request);}
