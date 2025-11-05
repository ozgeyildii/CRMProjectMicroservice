package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.CampaignProduct;
import com.etiya.catalogservice.repository.CampaignProductRepository;
import com.etiya.catalogservice.service.abstracts.CampaignProductService;
import com.etiya.catalogservice.service.dtos.requests.campaignproduct.CreateCampaignProductRequest;
import com.etiya.catalogservice.service.dtos.responses.campaignproduct.CreatedCampaignProductResponse;
import com.etiya.catalogservice.service.mappings.CampaignProductMapper;
import org.springframework.stereotype.Service;

@Service
public class CampaignProductServiceImpl implements CampaignProductService {

    private final CampaignProductRepository campaignProductRepository;

    public CampaignProductServiceImpl(CampaignProductRepository campaignProductRepository) {
        this.campaignProductRepository = campaignProductRepository;
    }

    @Override
    public CreatedCampaignProductResponse add(CreateCampaignProductRequest request) {
        CampaignProduct campaignProduct = CampaignProductMapper.INSTANCE.campaignProductFromCreateCampaignProductRequest(request);
        CampaignProduct saved = campaignProductRepository.save(campaignProduct);
        return CampaignProductMapper.INSTANCE.createdCampaignProductResponseFromCampaignProduct(saved);
    }

}
