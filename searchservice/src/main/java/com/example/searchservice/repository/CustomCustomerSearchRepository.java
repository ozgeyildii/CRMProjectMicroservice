package com.example.searchservice.repository;

import com.example.searchservice.domain.CustomerSearch;

import java.util.List;

public interface CustomCustomerSearchRepository {
    List<CustomerSearch> searchDynamic(
            String id,
            String customerNumber,
            String accountNumber,
            String nationalId,
            String firstName,
            String lastName,
            String value,
            int page,
            int size
    );
}