package com.etiya.customerservice.service.concretes;

import com.etiya.customerservice.domain.entities.City;
import com.etiya.customerservice.domain.entities.District;
import com.etiya.customerservice.service.abstracts.*;
import com.etiya.customerservice.service.requests.address.CreateAddressRequest;
import com.etiya.customerservice.service.requests.contactmedium.CreateContactMediumRequest;
import com.etiya.customerservice.service.requests.individualcustomerorchestrator.CreateFullIndividualCustomerRequest;
import com.etiya.customerservice.service.requests.individualcustomers.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.address.CreatedAddressResponse;
import com.etiya.customerservice.service.responses.contactmedium.CreatedContactMediumResponse;
import com.etiya.customerservice.service.responses.individualcustomerorchestrator.CreatedFullIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomers.CreatedIndividualCustomerResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerOrchestratorServiceImpl implements CustomerOrchestratorService {

    private final IndividualCustomerService individualCustomerService;
    private final AddressService addressService;
    private final ContactMediumService contactMediumService;
    private final CityService cityService;
    private final DistrictService districtService;

    public CustomerOrchestratorServiceImpl(IndividualCustomerService individualCustomerService, AddressService addressService, ContactMediumService contactMediumService, CityService cityService, DistrictService districtService) {
        this.individualCustomerService = individualCustomerService;
        this.addressService = addressService;
        this.contactMediumService = contactMediumService;
        this.cityService = cityService;
        this.districtService = districtService;
    }

    @Override
    @Transactional
    public CreatedFullIndividualCustomerResponse createFullCustomer(
            CreateFullIndividualCustomerRequest request) {

        CreateIndividualCustomerRequest individualReq = request.getIndividualCustomer();
        CreatedIndividualCustomerResponse createdIndividualCustomer = individualCustomerService.add(individualReq);
        CreatedFullIndividualCustomerResponse createdCustomer = new CreatedFullIndividualCustomerResponse();
        createdCustomer.setIndividualCustomer(createdIndividualCustomer);
        List<CreatedAddressResponse> createdAddresses = new ArrayList<>();
        List<CreatedContactMediumResponse> createdContactMediums = new ArrayList<>();
        if (request.getAddresses() != null) {
            for (CreateAddressRequest a : request.getAddresses()) {
                City city = cityService.findOrCreateByName(a.getCityName());
                District district = districtService.findOrCreateByNameAndCity(a.getDistrictName(), city);
                CreateAddressRequest addrReq = new CreateAddressRequest();
                addrReq.setCityId(city.getId());
                addrReq.setCityName(city.getName());
                addrReq.setDistrictId(district.getId());
                addrReq.setDistrictName(district.getName());
                addrReq.setStreet(a.getStreet());
                addrReq.setHouseNumber(a.getHouseNumber());
                addrReq.setDescription(a.getDescription());
                addrReq.setDefault(a.isDefault());
                addrReq.setCustomerId(createdCustomer.getIndividualCustomer().getId());

                CreatedAddressResponse createdAddressResponse= addressService.add(addrReq);
                createdAddresses.add(createdAddressResponse);
            }
            createdCustomer.setAddresses(createdAddresses);
        }

        if (request.getContactMediums() != null) {
            for (CreateContactMediumRequest cm : request.getContactMediums()) {
                cm.setCustomerId(createdCustomer.getIndividualCustomer().getId());
                CreatedContactMediumResponse createdContactMediumResponse= contactMediumService.add(cm);
                createdContactMediums.add(createdContactMediumResponse);
            }
            createdCustomer.setContactMediums(createdContactMediums);
        }

        return createdCustomer;
    }
}