package com.example.searchservice.service;

import com.example.searchservice.domain.Address;
import com.example.searchservice.domain.ContactMedium;
import com.example.searchservice.domain.CustomerSearch;

import java.util.List;
import java.util.UUID;

public interface CustomerSearchService {

    void add(CustomerSearch customerSearch);
    List<CustomerSearch> findAll();
    void delete(String id);
    //CustomerSearch getCustomerSearchById(UUID id);
    void addAddress(Address address,UUID customerId);
    void updateAddress(Address address, UUID customerId);
    void deleteAddress(int id,UUID customerId);

    void addContactMedium(ContactMedium contactMedium, UUID customerId);
    void updateContactMedium(ContactMedium contactMedium, UUID customerId);
    void deleteContactMedium(int id,UUID customerId);
}
