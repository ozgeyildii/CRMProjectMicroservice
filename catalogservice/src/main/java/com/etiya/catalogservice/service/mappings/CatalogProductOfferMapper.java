package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.CatalogProductOffer;
import com.etiya.catalogservice.service.dtos.requests.catalogproductoffer.CreateCatalogProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.catalogproductoffer.CreatedCatalogProductOfferResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CatalogProductOfferMapper {

    CatalogProductOfferMapper INSTANCE = Mappers.getMapper(CatalogProductOfferMapper.class);

    @Mapping(target = "catalog.id", source = "catalogId")
    @Mapping(target = "productOffer.id", source = "productOfferId")
    CatalogProductOffer catalogProductOfferFromCreateCatalogProductOfferRequest(CreateCatalogProductOfferRequest request);

    @Mapping(target = "catalogId", source = "catalog.id")
    @Mapping(target = "productOfferId", source = "productOffer.id")
    @Mapping(target = "catalogName", source = "catalog.name")
    @Mapping(target = "productOfferName", source = "productOffer.name")
    CreatedCatalogProductOfferResponse createdCatalogProductOfferResponseFromCatalogProductOffer(CatalogProductOffer catalogProductOffer);


}