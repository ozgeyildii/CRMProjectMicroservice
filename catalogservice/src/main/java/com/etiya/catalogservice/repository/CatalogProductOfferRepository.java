package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.CatalogProductOffer;
import com.etiya.common.responses.CampaignProductOfferResponse;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogProductOfferRepository extends JpaRepository<CatalogProductOffer, Integer> {

}
