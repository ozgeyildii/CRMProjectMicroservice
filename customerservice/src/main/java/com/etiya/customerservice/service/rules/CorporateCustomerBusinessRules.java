package com.etiya.customerservice.service.rules;

import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.customerservice.domain.entities.CorporateCustomer;
import com.etiya.customerservice.repository.CorporateCustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CorporateCustomerBusinessRules extends CustomerBusinessRules<CorporateCustomer> {

    private final CorporateCustomerRepository corporateCustomerRepository;

    public CorporateCustomerBusinessRules(CorporateCustomerRepository corporateCustomerRepository) {
        super(corporateCustomerRepository);
        this.corporateCustomerRepository = corporateCustomerRepository;
    }

    public void checkIfCorporateCustomerExistsByTaxNumber(String taxNumber) {
        if(corporateCustomerRepository.existsByTaxNumber(taxNumber)) {
            throw new BusinessException("Corporate Customer already exists with tax number: " + taxNumber);
        }
    }
}