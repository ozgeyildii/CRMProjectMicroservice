package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.Product;
import com.etiya.catalogservice.repository.ProductRepository;
import com.etiya.catalogservice.service.abstracts.ProductService;
import com.etiya.catalogservice.service.dtos.requests.CreateProductRequest;
import com.etiya.catalogservice.service.dtos.responses.CreatedProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public CreatedProductResponse add(CreateProductRequest request) {
        Product mappingProduct = new Product(request.getName(),request.getDescription(),request.getPrice());
        Product createdProduct = productRepository.save(mappingProduct);
        CreatedProductResponse response = new CreatedProductResponse();
        response.setId(createdProduct.getId());
        return response;
    }
}