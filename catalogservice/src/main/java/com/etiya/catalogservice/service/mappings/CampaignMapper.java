package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.Campaign;
import com.etiya.catalogservice.service.dtos.requests.campaign.CreateCampaignRequest;
import com.etiya.catalogservice.service.dtos.responses.campaign.CreatedCampaignResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CampaignMapper {
    CampaignMapper INSTANCE = Mappers.getMapper(CampaignMapper.class);

    Campaign campaignFromCampaignRequest(CreateCampaignRequest request);
    CreatedCampaignResponse campaignResponseFromCampaign(Campaign campaign);
}
