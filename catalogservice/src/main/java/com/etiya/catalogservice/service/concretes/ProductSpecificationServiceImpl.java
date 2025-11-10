package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.ProductOfferSpecification;
import com.etiya.catalogservice.repository.ProductSpecificationRepository;
import com.etiya.catalogservice.service.abstracts.ProductSpecificationService;
import com.etiya.catalogservice.service.dtos.requests.productofferspec.CreateProductOfferSpecificationRequest;
import com.etiya.catalogservice.service.dtos.responses.productspec.CreatedProductSpecificationResponse;
import com.etiya.catalogservice.service.mappings.ProductSpecificationMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductSpecificationServiceImpl implements ProductSpecificationService {
    private final ProductSpecificationRepository productSpecificationRepository;

    public ProductSpecificationServiceImpl(ProductSpecificationRepository productSpecificationRepository) {
        this.productSpecificationRepository = productSpecificationRepository;
    }

    @Override
    public CreatedProductSpecificationResponse add(CreateProductOfferSpecificationRequest request) {
        ProductOfferSpecification productSpec = ProductSpecificationMapper.INSTANCE.productSpecFromCreateProductSpecRequest(request);
        ProductOfferSpecification createdProductSpec = productSpecificationRepository.save(productSpec);
        CreatedProductSpecificationResponse response = ProductSpecificationMapper.INSTANCE.createdProductSpecResponseFromProductSpec(createdProductSpec);
        return response;    }
}
