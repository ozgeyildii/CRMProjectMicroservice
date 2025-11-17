package com.etiya.customerservice.service.concretes;

import com.etiya.customerservice.service.abstracts.*;
import com.etiya.customerservice.service.requests.individualcustomerorchestrator.CreateFullIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.address.CreatedAddressResponse;
import com.etiya.customerservice.service.responses.contactmedium.CreatedContactMediumResponse;
import com.etiya.customerservice.service.responses.individualcustomerorchestrator.CreatedFullIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomers.CreatedIndividualCustomerResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerOrchestratorServiceImpl implements CustomerOrchestratorService {

    private final IndividualCustomerService individualCustomerService;
    private final AddressService addressService;
    private final ContactMediumService contactMediumService;

    public CustomerOrchestratorServiceImpl(
            IndividualCustomerService individualCustomerService,
            AddressService addressService,
            ContactMediumService contactMediumService) {

        this.individualCustomerService = individualCustomerService;
        this.addressService = addressService;
        this.contactMediumService = contactMediumService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreatedFullIndividualCustomerResponse createFullCustomer(CreateFullIndividualCustomerRequest request) {
        CreatedIndividualCustomerResponse createdCustomer =
                individualCustomerService.add(request.getIndividualCustomer());

        List<CreatedAddressResponse> createdAddresses =
                Optional.ofNullable(request.getAddresses())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(addr -> {
                            addr.setCustomerId(createdCustomer.getId());
                            return addressService.add(addr);
                        }).toList();

        List<CreatedContactMediumResponse> createdContactMediums =
                Optional.ofNullable(request.getContactMediums())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(cm -> {
                            cm.setCustomerId(createdCustomer.getId());
                            return contactMediumService.add(cm);
                        }).toList();

        return new CreatedFullIndividualCustomerResponse(
                createdCustomer,
                createdAddresses,
                createdContactMediums
        );
    }
}