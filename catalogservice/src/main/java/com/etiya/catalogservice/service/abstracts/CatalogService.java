package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.domain.Catalog;
import com.etiya.catalogservice.service.dtos.requests.catalog.CreateCatalogRequest;
import com.etiya.catalogservice.service.dtos.responses.catalog.CreatedCatalogResponse;

public interface CatalogService {

    CreatedCatalogResponse createCatalog(CreateCatalogRequest request);
    Catalog getById(int id);
}
