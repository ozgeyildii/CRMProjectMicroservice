package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.ProductOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOfferRepository extends JpaRepository<ProductOffer, Integer> {
    List<ProductOffer> findByCampaignProductOffers_Campaign_Id(int campaignId);


}