package com.example.searchservice.service;

import com.example.searchservice.domain.Address;
import com.example.searchservice.domain.ContactMedium;
import com.example.searchservice.domain.CustomerSearch;

import java.time.LocalDateTime;
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
    void softDeleteAddress(int id,UUID customerId,String deletedDate);

    void addContactMedium(ContactMedium contactMedium);
    void updateContactMedium(ContactMedium contactMedium);
    void deleteContactMedium(int id,UUID customerId);
    void softDeleteContactMedium(int id,UUID customerId,String deletedDate);

    List<CustomerSearch> searchDynamic(
            String id,
            String customerNumber,
            String nationalId,
            String firstName,
            String lastName,
            String value,
            int page,
            int size
    );

    List<CustomerSearch> searchAllFields(String keyword);
    List<CustomerSearch> searchByMatchedName(String name);
    List<CustomerSearch> searchByExactValue(String nationalId);
    List<CustomerSearch> searchBySimilarName(String name);
    List<CustomerSearch> searchByDateRange(String start, String end);
    List<CustomerSearch> searchByNameAndGender(String name, String gender);
}
