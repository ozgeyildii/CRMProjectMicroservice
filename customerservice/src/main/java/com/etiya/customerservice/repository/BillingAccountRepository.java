package com.etiya.customerservice.repository;

import com.etiya.customerservice.domain.entities.BillingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BillingAccountRepository extends JpaRepository<BillingAccount, Integer> {

    List<BillingAccount> findByCustomer_Id(UUID customerId);
}
