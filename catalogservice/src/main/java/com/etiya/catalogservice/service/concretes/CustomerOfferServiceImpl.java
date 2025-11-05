package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.CustomerOffer;
import com.etiya.catalogservice.repository.CustomerOfferRepository;
import com.etiya.catalogservice.service.abstracts.CustomerOfferService;
import com.etiya.catalogservice.service.dtos.requests.customeroffer.CreateCustomerOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.customeroffer.CreatedCustomerOfferResponse;
import com.etiya.catalogservice.service.mappings.CustomerOfferMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerOfferServiceImpl implements CustomerOfferService {

    private final CustomerOfferRepository customerOfferRepository;

    public CustomerOfferServiceImpl(CustomerOfferRepository customerOfferRepository) {
        this.customerOfferRepository = customerOfferRepository;
    }

    @Override
    public CreatedCustomerOfferResponse add(CreateCustomerOfferRequest request) {
        CustomerOffer customerOffer = CustomerOfferMapper.INSTANCE
                .customerOfferFromCreateCustomerOfferRequest(request);

        CustomerOffer saved = customerOfferRepository.save(customerOffer);
        return CustomerOfferMapper.INSTANCE
                .createdCustomerOfferResponseFromCustomerOffer(saved);
    }
}
