package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.CampaignProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignProductRepository extends JpaRepository<CampaignProduct, Integer> {
}
