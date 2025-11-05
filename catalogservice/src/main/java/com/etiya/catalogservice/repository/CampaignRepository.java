package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
    boolean existsByCampaignCode(String campaignCode);

}
