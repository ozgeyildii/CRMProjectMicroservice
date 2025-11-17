package com.etiya.customerservice.service.concretes;

import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.customerservice.domain.entities.Customer;
import com.etiya.customerservice.repository.IndividualCustomerRepository;
import com.etiya.customerservice.service.abstracts.CustomerService;
import com.etiya.customerservice.service.abstracts.IndividualCustomerService;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final IndividualCustomerRepository individualCustomerRepository;
    private final IndividualCustomerService individualCustomerService;

    public CustomerServiceImpl(IndividualCustomerRepository individualCustomerRepository, IndividualCustomerService individualCustomerService) {
        this.individualCustomerRepository = individualCustomerRepository;
        this.individualCustomerService = individualCustomerService;
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

    @Override
    public Customer getByEntityId(UUID id) {
        return individualCustomerService.findById(id);
    }

}
