package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.ProductSpecification;
import com.etiya.catalogservice.service.dtos.requests.productspec.CreateProductSpecificationRequest;
import com.etiya.catalogservice.service.dtos.responses.productspec.CreatedProductSpecificationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductSpecificationMapper {

    ProductSpecificationMapper INSTANCE = Mappers.getMapper(ProductSpecificationMapper.class);

    ProductSpecification productSpecFromCreateProductSpecRequest(CreateProductSpecificationRequest createProductSpecificationRequest);
    CreatedProductSpecificationResponse createdProductSpecResponseFromProductSpec(ProductSpecification productSpecification);
}
