package com.etiya.catalogservice.service.mappings;
import com.etiya.catalogservice.domain.Product;
import com.etiya.catalogservice.service.dtos.requests.product.CreateProductRequest;
import com.etiya.catalogservice.service.dtos.responses.product.CreatedProductResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    @Mapping(target = "catalog.id", source = "catalogId")
    @Mapping(target = "productSpecification.id", source = "specId")
    Product productFromCreateProductRequest(CreateProductRequest createProductRequest);
    @Mapping(target = "catalogId", source = "catalog.id")
    @Mapping(target = "specId", source = "productSpecification.id")
    CreatedProductResponse createdProductResponseFromProduct(Product product);
}
