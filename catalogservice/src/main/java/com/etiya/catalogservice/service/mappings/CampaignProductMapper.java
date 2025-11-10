package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.CampaignProductOffer;
import com.etiya.catalogservice.service.dtos.requests.campaignproductoffer.CreateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.campaignproductoffer.CreatedCampaignProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CampaignProductMapper {
    CampaignProductMapper INSTANCE = Mappers.getMapper(CampaignProductMapper.class);

    //@Mapping(target = "id", ignore = true) --> Hata yaşarsan yorumdan çıkar
    @Mapping(target = "campaign.id", source = "campaignId")
    @Mapping(target = "productOffer.id", source = "productOfferId")
    CampaignProductOffer campaignProductFromCreateCampaignProductRequest(CreateCampaignProductOfferRequest request);

    @Mapping(source = "campaign.id", target = "campaignId")
    @Mapping(source = "productOffer.id", target = "productOfferId")
    CreatedCampaignProductResponse createdCampaignProductResponseFromCampaignProduct(CampaignProductOffer campaignProductOffer);

}
