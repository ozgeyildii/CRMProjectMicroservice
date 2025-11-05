package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.CatalogProductOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogProductOfferRepository extends JpaRepository<CatalogProductOffer, Integer> {
}