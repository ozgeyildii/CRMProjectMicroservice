package com.etiya.customerservice.service.abstracts;


import com.etiya.common.responses.CustomerResponse;
import com.etiya.customerservice.domain.entities.Customer;

import java.util.UUID;

public interface CustomerService {
    boolean existsById(UUID id);
    String getCustomerType(UUID customerId);
    Customer getByEntityId(UUID id);


}
