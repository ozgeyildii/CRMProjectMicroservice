package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CustomerOfferService;
import com.etiya.catalogservice.service.dtos.requests.customeroffer.CreateCustomerOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.customeroffer.CreatedCustomerOfferResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer-offers")
public class CustomerOfferController {

    private final CustomerOfferService customerOfferService;

    public CustomerOfferController(CustomerOfferService customerOfferService) {
        this.customerOfferService = customerOfferService;
    }

    @PostMapping
    public CreatedCustomerOfferResponse add(@RequestBody CreateCustomerOfferRequest request) {
        return customerOfferService.add(request);
    }
}