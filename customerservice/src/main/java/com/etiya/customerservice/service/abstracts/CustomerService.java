package com.etiya.customerservice.service.abstracts;


public interface CustomerService {
    boolean existsById(int id);

    String getCustomerType(int customerId);
}
