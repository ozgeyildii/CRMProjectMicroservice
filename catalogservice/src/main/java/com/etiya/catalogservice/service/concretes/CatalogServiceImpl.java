package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.Catalog;
import com.etiya.catalogservice.repository.CatalogRepository;
import com.etiya.catalogservice.service.abstracts.CatalogService;
import com.etiya.catalogservice.service.dtos.requests.catalog.CreateCatalogRequest;
import com.etiya.catalogservice.service.dtos.responses.catalog.CreatedCatalogResponse;
import com.etiya.catalogservice.service.mappings.CatalogMapper;
import org.springframework.stereotype.Service;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public CreatedCatalogResponse createCatalog(CreateCatalogRequest request) {
        Catalog catalog = CatalogMapper.INSTANCE.catalogFromCreateCatalogRequest(request);

        Catalog parent = catalogRepository.findById(request.getParentId())
                .orElseThrow(() -> new RuntimeException("Parent catalog not found"));
        catalog.setParent(parent);


        catalog = catalogRepository.save(catalog);
        return CatalogMapper.INSTANCE.createdCatalogResponseFromCatalog(catalog);
    }

    @Override
    public Catalog getById(int id) {
        return catalogRepository.findById(id).orElseThrow(() -> new RuntimeException("Catalog Not Found"));
    }
}
