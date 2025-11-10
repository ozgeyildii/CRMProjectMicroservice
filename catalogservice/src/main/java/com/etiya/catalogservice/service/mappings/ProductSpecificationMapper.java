package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.ProductOfferSpecification;
import com.etiya.catalogservice.service.dtos.requests.productofferspec.CreateProductOfferSpecificationRequest;
import com.etiya.catalogservice.service.dtos.responses.productspec.CreatedProductSpecificationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductSpecificationMapper {

    ProductSpecificationMapper INSTANCE = Mappers.getMapper(ProductSpecificationMapper.class);

    ProductOfferSpecification productSpecFromCreateProductSpecRequest(CreateProductOfferSpecificationRequest createProductOfferSpecificationRequest);
    CreatedProductSpecificationResponse createdProductSpecResponseFromProductSpec(ProductOfferSpecification productOfferSpecification);
}
