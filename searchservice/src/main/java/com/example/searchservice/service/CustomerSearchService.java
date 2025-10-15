package com.example.searchservice.service;

import com.example.searchservice.domain.Address;
import com.example.searchservice.domain.CustomerSearch;

import java.util.List;
import java.util.UUID;

public interface CustomerSearchService {

    void add(CustomerSearch customerSearch);
    List<CustomerSearch> findAll();
    void delete(String id);
    //CustomerSearch getCustomerSearchById(UUID id);
    void addAddress(Address address);
    void updateAddress(Address address);
    void deleteAddress(int id,UUID customerId);
}
