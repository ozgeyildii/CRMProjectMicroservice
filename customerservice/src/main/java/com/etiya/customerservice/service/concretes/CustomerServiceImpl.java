package com.etiya.customerservice.service.concretes;

import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.common.responses.CustomerResponse;
import com.etiya.customerservice.domain.entities.Customer;
import com.etiya.customerservice.repository.CustomerRepository;
import com.etiya.customerservice.repository.IndividualCustomerRepository;
import com.etiya.customerservice.service.abstracts.CustomerService;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final IndividualCustomerRepository individualCustomerRepository;


    public CustomerServiceImpl(IndividualCustomerRepository individualCustomerRepository){
        this.individualCustomerRepository = individualCustomerRepository;
    }

    @Override
    public boolean existsById(UUID id) {
        return false;
    }

    @Override
    public String getCustomerType(UUID id) {
        if (individualCustomerRepository.existsById(id)) {
            return "INDIVIDUAL";
        }

        throw new BusinessException("Customer not found with id: " + id);
    }

}