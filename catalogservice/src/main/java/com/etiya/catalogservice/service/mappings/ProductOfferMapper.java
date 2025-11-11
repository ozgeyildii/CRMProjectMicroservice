package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.ProductOffer;
import com.etiya.catalogservice.service.dtos.requests.productoffer.CreateProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.productoffer.CreatedProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.productoffer.GetListProductOfferResponse;
import com.etiya.common.responses.ProductOfferResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductOfferMapper {

    ProductOfferMapper INSTANCE = Mappers.getMapper(ProductOfferMapper.class);

    ProductOffer productOfferFromCreateProductOfferRequest(CreateProductOfferRequest request);

    @Mapping(target = "productId", source = "product.id")
    CreatedProductOfferResponse createdProductOfferResponseFromProductOffer(ProductOffer productOffer);


    ProductOfferResponse productOfferResponseFromProductOffer(ProductOffer productOffer);

    List<GetListProductOfferResponse> getListProductOfferResponseFromProductOfferList(List<ProductOffer> productOffers);


}