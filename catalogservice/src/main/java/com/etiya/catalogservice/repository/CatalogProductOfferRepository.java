package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.CatalogProductOffer;
import com.etiya.common.responses.CampaignProductOfferResponse;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogProductOfferRepository extends JpaRepository<CatalogProductOffer, Integer> {
    @Query("""
        SELECT CampaignProductOfferResponse(
            cpo.id,
            po.id,
            po.name,
            p.price,
            c.discountRate,
            (p.price * (1 - c.discountRate)),
            c.id,
            c.name,
            c.campaignCode
        )
        FROM CampaignProductOffer cpo
        JOIN cpo.productOffer po
        JOIN po.product p
        JOIN cpo.campaign c
        WHERE cpo.id = :id
    """)
    CampaignProductOfferResponse findCampaignOfferById(@Param("id") int id);
}
