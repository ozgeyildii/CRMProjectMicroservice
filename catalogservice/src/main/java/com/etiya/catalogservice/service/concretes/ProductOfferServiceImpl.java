package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.ProductOffer;
import com.etiya.catalogservice.repository.ProductOfferRepository;
import com.etiya.catalogservice.service.abstracts.ProductOfferService;
import com.etiya.catalogservice.service.abstracts.ProductService;
import com.etiya.catalogservice.service.dtos.requests.productoffer.CreateProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.productoffer.CreatedProductOfferResponse;
import com.etiya.catalogservice.service.mappings.ProductOfferMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductOfferServiceImpl implements ProductOfferService {

    private final ProductOfferRepository productOfferRepository;
    private final ProductService productService;

    public ProductOfferServiceImpl(ProductOfferRepository productOfferRepository,
                                   ProductService productService) {
        this.productOfferRepository = productOfferRepository;
        this.productService = productService;
    }

    @Override
    public CreatedProductOfferResponse add(CreateProductOfferRequest request) {
        ProductOffer productOffer = ProductOfferMapper.INSTANCE.productOfferFromCreateProductOfferRequest(request);
        productOffer.setProduct(productService.getProductById(request.getProductId()));
        productOffer = productOfferRepository.save(productOffer);
        return ProductOfferMapper.INSTANCE.createdProductOfferResponseFromProductOffer(productOffer);
    }

    @Override
    public ProductOffer getById(int id) {
        return productOfferRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Offer Not Found"));
    }
}
