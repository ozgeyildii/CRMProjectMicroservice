package com.etiya.customerservice.service.concretes;

import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.common.localization.LocalizationService;
import com.etiya.common.responses.CustomerResponse;
import com.etiya.customerservice.domain.entities.Customer;
import com.etiya.customerservice.repository.CustomerRepository;
import com.etiya.customerservice.repository.IndividualCustomerRepository;
import com.etiya.customerservice.service.abstracts.CustomerService;
import com.etiya.customerservice.service.abstracts.IndividualCustomerService;
import com.etiya.customerservice.service.messages.Messages;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final IndividualCustomerRepository individualCustomerRepository;
    private final IndividualCustomerService individualCustomerService;
    private final LocalizationService localizationService;

    public CustomerServiceImpl(IndividualCustomerRepository individualCustomerRepository, IndividualCustomerService individualCustomerService, LocalizationService localizationService) {
        this.individualCustomerRepository = individualCustomerRepository;
        this.individualCustomerService = individualCustomerService;
        this.localizationService = localizationService;
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

        throw new BusinessException(localizationService.getMessage(Messages.CustomerNotExist));
    }

    @Override
    public Customer getByEntityId(UUID id) {
        return individualCustomerService.findById(id);
    }

}
