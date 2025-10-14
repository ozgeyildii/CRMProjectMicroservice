package com.etiya.customerservice.service.abstracts;

import com.etiya.customerservice.service.requests.corporateCustomer.CreateCorporateCustomerRequest;
import com.etiya.customerservice.service.responses.corporateCustomer.CreatedCorporateCustomerResponse;

public interface CorporateCustomerService {
    CreatedCorporateCustomerResponse add(CreateCorporateCustomerRequest request);
}
