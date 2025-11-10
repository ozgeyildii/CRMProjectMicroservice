package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.ProductOfferSpecification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSpecificationRepository extends JpaRepository<ProductOfferSpecification,Integer> {
}
