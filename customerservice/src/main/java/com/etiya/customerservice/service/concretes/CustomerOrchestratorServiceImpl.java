package com.etiya.customerservice.service.concretes;

import com.etiya.common.events.address.CreateAddressEvent;
import com.etiya.common.events.contactmedium.CreateContactMediumEvent;
import com.etiya.common.events.customer.CreateCustomerEvent;
import com.etiya.customerservice.infrastructure.OutboxService;
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
import java.util.stream.Stream;

@Service
public class CustomerOrchestratorServiceImpl implements CustomerOrchestratorService {

    private final IndividualCustomerService individualCustomerService;
    private final AddressService addressService;
    private final ContactMediumService contactMediumService;
    private final OutboxService outboxService;

    public CustomerOrchestratorServiceImpl(
            IndividualCustomerService individualCustomerService,
            AddressService addressService,
            ContactMediumService contactMediumService, OutboxService outboxService) {

        this.individualCustomerService = individualCustomerService;
        this.addressService = addressService;
        this.contactMediumService = contactMediumService;
        this.outboxService = outboxService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreatedFullIndividualCustomerResponse createFullCustomer(CreateFullIndividualCustomerRequest request) {

        // 1️ Individual Customer oluşturulur
        CreatedIndividualCustomerResponse createdCustomer =
                individualCustomerService.add(request.getIndividualCustomer());

        String customerId = createdCustomer.getId().toString();


        // 2️ Address’ler functional şekilde işlenir (null-safe + map)
        List<CreatedAddressResponse> createdAddresses =
                Optional.ofNullable(request.getAddresses())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(addr -> {
                            addr.setCustomerId(createdCustomer.getId());
                            return addressService.add(addr);
                        })
                        .toList();

        // 3️ Contact Medium’lar functional şekilde işlenir (null-safe + map)
        List<CreatedContactMediumResponse> createdContactMediums =
                Optional.ofNullable(request.getContactMediums())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(cm -> {
                            cm.setCustomerId(createdCustomer.getId());
                            return contactMediumService.add(cm);
                        })
                        .toList();

        Stream.concat(
                Stream.of(
                        new CreateCustomerEvent(
                                createdCustomer.getId(),
                                createdCustomer.getCustomerNumber(),
                                createdCustomer.getFirstName(),
                                createdCustomer.getLastName(),
                                createdCustomer.getNationalId(),
                                createdCustomer.getDateOfBirth().toString(),
                                createdCustomer.getMotherName(),
                                createdCustomer.getFatherName(),
                                createdCustomer.getGender()
                        )
                ),
                Stream.concat(
                        createdAddresses.stream().map(a -> new CreateAddressEvent(
                                a.getId(),
                                a.getStreet(),
                                a.getHouseNumber(),
                                a.getDescription(),
                                a.isDefault(),
                                a.getDistrictId(),
                                a.getDistrictName(),
                                a.getCityId(),
                                a.getCityName(),
                                createdCustomer.getId()
                        )),
                        createdContactMediums.stream().map(c -> new CreateContactMediumEvent(
                                c.getId(),
                                c.getType().toString(),
                                c.getValue(),
                                c.isPrimary(),
                                createdCustomer.getId()
                        ))
                )
        )  .map(event -> {
                    String type;
                    if (event instanceof CreateCustomerEvent) type = "CUSTOMER";
                    else if (event instanceof CreateAddressEvent) type = "ADDRESS";
                    else if (event instanceof CreateContactMediumEvent) type = "CONTACT_MEDIUM";
                    else type = "UNKNOWN";
                    outboxService.save(event, type, customerId);
                    return event;
                })
                .toList();


        // 4⃣ Response nesnesi immutable şekilde oluşturulur
        return new CreatedFullIndividualCustomerResponse(
                createdCustomer,
                createdAddresses,
                createdContactMediums
        );
    }
}
