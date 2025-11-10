package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.CampaignProductOffer;
import com.etiya.catalogservice.repository.CampaignProductRepository;
import com.etiya.catalogservice.service.abstracts.CampaignProductService;
import com.etiya.catalogservice.service.dtos.requests.campaignproductoffer.CreateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.campaignproductoffer.CreatedCampaignProductResponse;
import com.etiya.catalogservice.service.mappings.CampaignProductMapper;
import org.springframework.stereotype.Service;

@Service
public class CampaignProductServiceImpl implements CampaignProductService {

    private final CampaignProductRepository campaignProductRepository;

    public CampaignProductServiceImpl(CampaignProductRepository campaignProductRepository) {
        this.campaignProductRepository = campaignProductRepository;
    }

    @Override
    public CreatedCampaignProductResponse add(CreateCampaignProductOfferRequest request) {
        CampaignProductOffer campaignProductOffer = CampaignProductMapper.INSTANCE.campaignProductFromCreateCampaignProductRequest(request);
        CampaignProductOffer saved = campaignProductRepository.save(campaignProductOffer);
        return CampaignProductMapper.INSTANCE.createdCampaignProductResponseFromCampaignProduct(saved);
    }

}
