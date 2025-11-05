package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.CustomerOffer;
import com.etiya.catalogservice.service.dtos.requests.customeroffer.CreateCustomerOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.customeroffer.CreatedCustomerOfferResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerOfferMapper {
    CustomerOfferMapper INSTANCE = Mappers.getMapper(CustomerOfferMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productOffer.id", source = "productOfferId")
    CustomerOffer customerOfferFromCreateCustomerOfferRequest(CreateCustomerOfferRequest request);

    @Mapping(target = "productOfferId", source = "productOffer.id")
    CreatedCustomerOfferResponse createdCustomerOfferResponseFromCustomerOffer(CustomerOffer customerOffer);
}
