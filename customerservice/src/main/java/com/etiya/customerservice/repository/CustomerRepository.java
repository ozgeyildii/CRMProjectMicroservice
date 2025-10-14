package com.etiya.customerservice.repository;

import com.etiya.customerservice.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomerRepository<T extends Customer> extends JpaRepository<T, Integer> {
    boolean existsById(int id);
}
