package com.etiya.customerservice.service.concretes;

import com.etiya.customerservice.domain.entities.CorporateCustomer;
import com.etiya.customerservice.repository.CorporateCustomerRepository;
import com.etiya.customerservice.service.abstracts.CorporateCustomerService;
import com.etiya.customerservice.service.mappings.CorporateCustomerMapper;
import com.etiya.customerservice.service.requests.corporateCustomer.CreateCorporateCustomerRequest;
import com.etiya.customerservice.service.responses.corporateCustomer.CreatedCorporateCustomerResponse;
import com.etiya.customerservice.service.rules.CorporateCustomerBusinessRules;
import org.springframework.stereotype.Service;

@Service
public class CorporateCustomerServiceImpl implements CorporateCustomerService {

    private final CorporateCustomerRepository corporateCustomerRepository;
    private final CorporateCustomerBusinessRules corporateCustomerBusinessRules;

    public CorporateCustomerServiceImpl(CorporateCustomerRepository corporateCustomerRepository, CorporateCustomerBusinessRules corporateCustomerBusinessRules) {
        this.corporateCustomerRepository = corporateCustomerRepository;
        this.corporateCustomerBusinessRules = corporateCustomerBusinessRules;
    }

    @Override
    public CreatedCorporateCustomerResponse add(CreateCorporateCustomerRequest request) {
        corporateCustomerBusinessRules.checkIfCorporateCustomerExistsByTaxNumber(request.getTaxNumber());

        CorporateCustomer corporateCustomer = CorporateCustomerMapper.INSTANCE
                .corporateCustomerFromCreateRequest(request);

        CorporateCustomer created = corporateCustomerRepository.save(corporateCustomer);

        return CorporateCustomerMapper.INSTANCE
                .createdResponseFromCorporateCustomer(created);
    }
}
