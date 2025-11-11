package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.CampaignProductOffer;
import com.etiya.common.responses.CampaignProductOfferResponse;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignProductOfferRepository extends JpaRepository<CampaignProductOffer, Integer> {
    @Query("""
                SELECT new com.etiya.common.responses.CampaignProductOfferResponse(
                    cpo.id,
                    po.id,
                    po.name,
                    po.price,
                    po.stock,
                    po.discountRate,
                    c.id,
                    c.name,
                    c.campaignCode
                )
                FROM CampaignProductOffer cpo
                JOIN cpo.productOffer po
                JOIN cpo.campaign c
                WHERE cpo.id = :id
            """)
    CampaignProductOfferResponse findCampaignOfferById(@Param("id") int id);

}
