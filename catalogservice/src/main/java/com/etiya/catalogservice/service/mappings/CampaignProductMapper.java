package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.CampaignProduct;
import com.etiya.catalogservice.service.dtos.requests.campaignproduct.CreateCampaignProductRequest;
import com.etiya.catalogservice.service.dtos.responses.campaignproduct.CreatedCampaignProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CampaignProductMapper {
    CampaignProductMapper INSTANCE = Mappers.getMapper(CampaignProductMapper.class);

     //@Mapping(target = "id", ignore = true) --> Hata yaşarsan yorumdan çıkar
    @Mapping(target = "campaign.id", source = "campaignId")
    @Mapping(target = "product.id", source = "productId")
    CampaignProduct campaignProductFromCreateCampaignProductRequest(CreateCampaignProductRequest request);

    @Mapping(source = "campaign.id", target = "campaignId")
    @Mapping(source = "product.id", target = "productId")
    CreatedCampaignProductResponse createdCampaignProductResponseFromCampaignProduct(CampaignProduct campaignProduct);

}
