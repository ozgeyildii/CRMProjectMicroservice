package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.Campaign;
import com.etiya.catalogservice.repository.CampaignRepository;
import com.etiya.catalogservice.service.abstracts.CampaignService;
import com.etiya.catalogservice.service.dtos.requests.campaign.CreateCampaignRequest;
import com.etiya.catalogservice.service.dtos.responses.campaign.CreatedCampaignResponse;
import com.etiya.catalogservice.service.dtos.responses.campaign.GetCampaignResponse;
import com.etiya.catalogservice.service.mappings.CampaignMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignServiceImpl  implements CampaignService {
    private final CampaignRepository campaignRepository;

    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;

    }

    @Override
    public CreatedCampaignResponse add(CreateCampaignRequest request) {
        if (campaignRepository.existsByCampaignCode(request.getCampaignCode())) {
            throw new IllegalArgumentException("Campaign code already exists: " + request.getCampaignCode());
        }


        Campaign campaign = CampaignMapper.INSTANCE.campaignFromCampaignRequest(request);
        Campaign saved = campaignRepository.save(campaign);
        return CampaignMapper.INSTANCE.campaignResponseFromCampaign(saved);
    }

    @Override
    public List<GetCampaignResponse> getAllCampaigns() {
        List<Campaign> campaigns = campaignRepository.findAll();
        return CampaignMapper.INSTANCE.getCampaignListResponseFromCampaign(campaigns);
    }


}
