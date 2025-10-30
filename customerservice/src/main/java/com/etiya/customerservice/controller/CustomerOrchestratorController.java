package com.etiya.customerservice.controller;

import com.etiya.customerservice.service.abstracts.CustomerOrchestratorService;
import com.etiya.customerservice.service.requests.individualcustomerorchestrator.CreateFullIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.individualcustomers.CreatedIndividualCustomerResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orchestrator")
public class CustomerOrchestratorController {

    private final CustomerOrchestratorService customerOrchestratorService;

    public CustomerOrchestratorController(CustomerOrchestratorService customerOrchestratorService) {
        this.customerOrchestratorService = customerOrchestratorService;
    }

    @PostMapping(value = "/full-individual-customers", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedIndividualCustomerResponse createFullCustomer(@Valid @RequestBody CreateFullIndividualCustomerRequest request) {
        return customerOrchestratorService.createFullCustomer(request);
    }
}