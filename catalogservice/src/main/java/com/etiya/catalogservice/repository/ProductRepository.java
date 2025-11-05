package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}