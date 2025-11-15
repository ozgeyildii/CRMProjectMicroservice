package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.Product;
import com.etiya.catalogservice.repository.ProductRepository;
import com.etiya.catalogservice.service.abstracts.ProductService;
import com.etiya.catalogservice.service.dtos.requests.product.CreateProductRequest;
import com.etiya.catalogservice.service.dtos.responses.product.CreatedProductResponse;
import com.etiya.catalogservice.service.mappings.ProductMapper;
import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.common.responses.ProductOfferResponse;
import org.springframework.stereotype.Service;

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
    public void addProductByEntity(Product product) {
        productRepository.save(product);
    }

//    @Override
//    public ProductOfferResponse getById(int id) {
//        return productRepository.findById(id).stream().map(this::mapToResponse).findFirst().orElseThrow(()->new BusinessException("Product not found"));
//    }


//    @Override
//    public ProductResponse getById(String id) {
//        return productRepository.findById(id).stream().map(this::mapToResponse).findFirst().orElseThrow(()->new BusinessException("Product not found"));
//    }
//
//    private ProductResponse mapToResponse(Product product){
//        ProductResponse response = new ProductResponse();
//        response.setId(product.getId());
//        response.setName(product.getName());
//        response.setPrice(product.getPrice());
//        return response;
//    }
}
