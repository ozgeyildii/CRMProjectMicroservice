package com.etiya.customerservice.repository;

import com.etiya.customerservice.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface CustomerRepository<T extends Customer> extends JpaRepository<T, UUID> {
    boolean existsById(UUID id);
}
