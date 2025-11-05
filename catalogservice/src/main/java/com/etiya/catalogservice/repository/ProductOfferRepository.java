package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.ProductOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOfferRepository extends JpaRepository<ProductOffer, Integer> {
}