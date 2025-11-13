package com.etiya.basketservice.service.mapping;

import com.etiya.basketservice.domain.BasketItem;
import com.etiya.basketservice.service.dto.response.CreatedBasketItemResponse;
import com.etiya.common.responses.CampaignProductOfferResponse;
import com.etiya.common.responses.ProductOfferResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BasketMapper {
    BasketMapper INSTANCE = Mappers.getMapper(BasketMapper.class);

    // Normal product offer
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "basketId", ignore = true)
    @Mapping(target = "campaignProductOfferId", ignore = true)
    @Mapping(target = "productOfferId", source = "id")
    @Mapping(target = "productOfferName", source = "name")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "quantity", constant = "1")
    BasketItem fromProductOfferResponse(ProductOfferResponse productOfferResponse);

    // Campaign product offer
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "basketId", ignore = true)
    @Mapping(target = "productOfferId", source = "productOfferId")
    @Mapping(target = "productOfferName", source = "productOfferName")
    @Mapping(target = "campaignProductOfferId", ignore = true)
    @Mapping(target = "price", source = "price")
    @Mapping(target = "discountRate", source = "discountRate")
    @Mapping(target = "quantity", constant = "1")
    BasketItem fromCampaignProductOfferResponse(CampaignProductOfferResponse campaignResponse);

    // BasketItem → CreatedBasketItemResponse
    @Mapping(target = "basketId", source = "basketId")
    @Mapping(target = "basketItemId", source = "id")
    @Mapping(target = "productOfferName", source = "productOfferName")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "campaignProductOfferId", source = "campaignProductOfferId")
    @Mapping(target = "catalogProductOfferId", source = "catalogProductOfferId")
    @Mapping(target = "discountedPrice", source = "discountedPrice")
    @Mapping(target = "discountRate", source = "discountRate")
    @Mapping(target = "type", expression = "java(item.getCampaignProductOfferId() != 0 ? \"CAMPAIGN\" : \"OFFER\")")
    CreatedBasketItemResponse toCreatedBasketItemResponse(BasketItem item);
}

