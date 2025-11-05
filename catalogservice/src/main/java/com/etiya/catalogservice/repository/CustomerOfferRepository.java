package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.CustomerOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOfferRepository extends JpaRepository<CustomerOffer, Integer> {
}
