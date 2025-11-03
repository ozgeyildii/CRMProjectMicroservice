package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.Product;
import com.etiya.catalogservice.repository.ProductRepository;
import com.etiya.catalogservice.service.abstracts.ProductService;
import com.etiya.catalogservice.service.dtos.requests.CreateProductRequest;
import com.etiya.catalogservice.service.dtos.responses.CreatedProductResponse;
import com.etiya.catalogservice.service.mappings.ProductMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public CreatedProductResponse add(CreateProductRequest request) {
       Product product = ProductMapper.INSTANCE.productFromCreateProductRequest(request);
       Product createdProduct = productRepository.save(product);
       CreatedProductResponse response = ProductMapper.INSTANCE.createdProductResponseFromProduct(createdProduct);
       return response;
    }
}