package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.CampaignProductOffer;
import com.etiya.catalogservice.repository.CampaignProductOfferRepository;
import com.etiya.catalogservice.service.abstracts.CampaignProductOfferService;
import com.etiya.catalogservice.service.dtos.requests.campaignproductoffer.CreateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.campaignproductoffer.CreatedCampaignProductResponse;
import com.etiya.catalogservice.service.mappings.CampaignProductMapper;
import com.etiya.common.responses.CampaignProductOfferResponse;
import org.springframework.stereotype.Service;

@Service
public class CampaignProductOfferServiceImpl implements CampaignProductOfferService {

    private final CampaignProductOfferRepository campaignProductOfferRepository;

    public CampaignProductOfferServiceImpl(CampaignProductOfferRepository campaignProductOfferRepository) {
        this.campaignProductOfferRepository = campaignProductOfferRepository;
    }

    @Override
    public CreatedCampaignProductResponse add(CreateCampaignProductOfferRequest request) {
        CampaignProductOffer campaignProductOffer = CampaignProductMapper.INSTANCE.campaignProductFromCreateCampaignProductRequest(request);
        CampaignProductOffer saved = campaignProductOfferRepository.save(campaignProductOffer);
        return CampaignProductMapper.INSTANCE.createdCampaignProductResponseFromCampaignProduct(saved);
    }

    @Override
    public CampaignProductOfferResponse getById(int id) {

        return campaignProductOfferRepository.findCampaignOfferById(id);
    }

}
