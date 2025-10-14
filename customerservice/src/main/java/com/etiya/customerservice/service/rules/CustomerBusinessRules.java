package com.etiya.customerservice.service.rules;

import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.customerservice.domain.entities.Customer;
import com.etiya.customerservice.repository.CustomerRepository;

public abstract class CustomerBusinessRules<T extends Customer> {

    private final CustomerRepository<T> customerRepository;

    public CustomerBusinessRules(CustomerRepository<T> customerRepository) {
        this.customerRepository = customerRepository;
    }


    public void checkIfCustomerExists(int id){
        if (!customerRepository.existsById(id)){
            throw new BusinessException("Customer with id " + id + " does not exist");
        }
    }
}
