package com.etiya.customerservice.repository;

import com.etiya.customerservice.domain.entities.CorporateCustomer;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporateCustomerRepository extends CustomerRepository<CorporateCustomer> {
    boolean existsByTaxNumber(String taxNumber);
}