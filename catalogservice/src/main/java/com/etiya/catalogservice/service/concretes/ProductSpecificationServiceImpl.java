package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.ProductSpecification;
import com.etiya.catalogservice.repository.ProductSpecificationRepository;
import com.etiya.catalogservice.service.abstracts.ProductSpecificationService;
import com.etiya.catalogservice.service.dtos.requests.productspec.CreateProductSpecificationRequest;
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
    public CreatedProductSpecificationResponse add(CreateProductSpecificationRequest request) {
        ProductSpecification productSpec = ProductSpecificationMapper.INSTANCE.productSpecFromCreateProductSpecRequest(request);
        ProductSpecification createdProductSpec = productSpecificationRepository.save(productSpec);
        CreatedProductSpecificationResponse response = ProductSpecificationMapper.INSTANCE.createdProductSpecResponseFromProductSpec(createdProductSpec);
        return response;    }
}
