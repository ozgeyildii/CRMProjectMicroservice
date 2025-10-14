package com.etiya.customerservice.service.rules;

import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.common.localization.LocalizationService;
import com.etiya.customerservice.domain.entities.IndividualCustomer;
import com.etiya.customerservice.repository.IndividualCustomerRepository;
import com.etiya.customerservice.service.messages.Messages;
import org.springframework.stereotype.Service;

@Service
public class IndividualCustomerBusinessRules extends CustomerBusinessRules<IndividualCustomer> {
    private final IndividualCustomerRepository individualCustomerRepository;
    private final LocalizationService localizationService;

    public IndividualCustomerBusinessRules(IndividualCustomerRepository individualCustomerRepository, LocalizationService localizationService) {
        super(individualCustomerRepository);
        this.individualCustomerRepository = individualCustomerRepository;
        this.localizationService = localizationService;
    }

    public void checkIfIndividualCustomerExistsByIdentityNumber(String nationalId) {
        if (individualCustomerRepository.existsByNationalId(nationalId)) {
            throw new BusinessException(localizationService.getMessage(Messages.NationalityIdentityExists));
        }
    }
}
