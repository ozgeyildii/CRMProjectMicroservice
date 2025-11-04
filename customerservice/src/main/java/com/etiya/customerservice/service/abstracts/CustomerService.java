package com.etiya.customerservice.service.abstracts;


import com.etiya.common.responses.CustomerResponse;

import java.util.UUID;

public interface CustomerService {
    boolean existsById(UUID id);
    String getCustomerType(UUID customerId);

}
