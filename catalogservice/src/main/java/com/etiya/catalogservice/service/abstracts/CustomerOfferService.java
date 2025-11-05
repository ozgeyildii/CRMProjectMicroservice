package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.requests.customeroffer.CreateCustomerOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.customeroffer.CreatedCustomerOfferResponse;

public interface CustomerOfferService {
    CreatedCustomerOfferResponse add(CreateCustomerOfferRequest request);

}
