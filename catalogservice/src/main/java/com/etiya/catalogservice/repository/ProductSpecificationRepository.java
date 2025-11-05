package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.ProductSpecification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSpecificationRepository extends JpaRepository<ProductSpecification,Integer> {
}
