package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.Product;
import com.etiya.catalogservice.service.dtos.responses.product.GetProductDetailResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("""
    SELECT new com.etiya.catalogservice.service.dtos.responses.product.GetProductDetailResponse(
        p.id,
        p.name,
        po.id,
        po.name,
        c.id,
        c.name
    )
    FROM Product p
    JOIN ProductOffer po ON p.productOffer.id= po.id
    LEFT JOIN CampaignProductOffer cpo ON cpo.productOffer.id = po.id
    LEFT JOIN Campaign c ON c.id = cpo.campaign.id
    WHERE p.id = :id
""")
    GetProductDetailResponse findCampaignOfferByProdId(int id);
    // product offer id ile product ilişkisi bulunacak (billing account id ile)
}