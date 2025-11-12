package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.Campaign;
import com.etiya.catalogservice.service.dtos.requests.campaign.CreateCampaignRequest;
import com.etiya.catalogservice.service.dtos.responses.campaign.CreatedCampaignResponse;
import com.etiya.catalogservice.service.dtos.responses.campaign.GetCampaignResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CampaignMapper {
    CampaignMapper INSTANCE = Mappers.getMapper(CampaignMapper.class);

    Campaign campaignFromCampaignRequest(CreateCampaignRequest request);
    CreatedCampaignResponse campaignResponseFromCampaign(Campaign campaign);

    GetCampaignResponse getCampaignResponseFromCampaign(Campaign campaign);
    List<GetCampaignResponse> getCampaignListResponseFromCampaign(List<Campaign> campaigns);
}
