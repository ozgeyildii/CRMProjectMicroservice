package com.etiya.catalogservice.service.mappings;
import com.etiya.catalogservice.domain.Product;
import com.etiya.catalogservice.service.dtos.requests.product.CreateProductRequest;
import com.etiya.catalogservice.service.dtos.responses.product.CreatedProductResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    Product productFromCreateProductRequest(CreateProductRequest createProductRequest);
    CreatedProductResponse createdProductResponseFromProduct(Product product);
}
