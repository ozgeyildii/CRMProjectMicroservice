package com.etiya.customerservice.service.concretes;

import com.etiya.customerservice.domain.entities.City;
import com.etiya.customerservice.domain.entities.District;
import com.etiya.customerservice.service.abstracts.*;
import com.etiya.customerservice.service.requests.address.CreateAddressRequest;
import com.etiya.customerservice.service.requests.addressorchestratorrequest.CreateFullAddressRequest;
import com.etiya.customerservice.service.requests.contactmedium.CreateContactMediumRequest;
import com.etiya.customerservice.service.requests.individualcustomerorchestrator.CreateFullIndividualCustomerRequest;
import com.etiya.customerservice.service.requests.individualcustomers.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.individualcustomers.CreatedIndividualCustomerResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
    public CreatedIndividualCustomerResponse createFullCustomer(
            CreateFullIndividualCustomerRequest request) {

        CreateIndividualCustomerRequest individualReq = request.getIndividualCustomer();
        CreatedIndividualCustomerResponse createdCustomer = individualCustomerService.add(individualReq);

        if (request.getAddresses() != null) {
            for (CreateFullAddressRequest a : request.getAddresses()) {
                City city = cityService.findOrCreateByName(a.getCity());
                District district = districtService.findOrCreateByNameAndCity(a.getDistrict(), city);

                CreateAddressRequest addrReq = new CreateAddressRequest();
                addrReq.setStreet(a.getStreet());
                addrReq.setHouseNumber(a.getHouseNumber());
                addrReq.setDescription(a.getDescription());
                addrReq.setDefault(a.isDefault());
                addrReq.setDistrictId(district.getId());
                addrReq.setCustomerId(createdCustomer.getId());

                addressService.add(addrReq);
            }
        }

        if (request.getContactMediums() != null) {
            for (CreateContactMediumRequest cm : request.getContactMediums()) {
                contactMediumService.addForCustomer(createdCustomer.getId(), cm);
            }
        }

        return createdCustomer;
    }
}