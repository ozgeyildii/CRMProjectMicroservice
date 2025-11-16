package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.Product;
import com.etiya.catalogservice.repository.ProductRepository;
import com.etiya.catalogservice.service.abstracts.ProductService;
import com.etiya.catalogservice.service.dtos.requests.product.CreateProductRequest;
import com.etiya.catalogservice.service.dtos.responses.product.CreatedProductResponse;
import com.etiya.catalogservice.service.dtos.responses.product.GetProductDetailResponse;
import com.etiya.catalogservice.service.mappings.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public CreatedProductResponse add(CreateProductRequest request) {
       Product product = ProductMapper.INSTANCE.productFromCreateProductRequest(request);
       Product createdProduct = productRepository.save(product);
       CreatedProductResponse response = ProductMapper.INSTANCE.createdProductResponseFromProduct(createdProduct);
       return response;
    }

    @Override
    public Product addProductByEntity(Product product) {
        return productRepository.save(product);
    }

}
