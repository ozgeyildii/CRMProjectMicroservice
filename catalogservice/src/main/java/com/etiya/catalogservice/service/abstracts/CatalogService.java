package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.domain.Catalog;
import com.etiya.catalogservice.service.dtos.requests.catalog.CreateCatalogRequest;
import com.etiya.catalogservice.service.dtos.responses.catalog.CreatedCatalogResponse;
import com.etiya.catalogservice.service.dtos.responses.catalog.GetListCatalogResponse;

import java.util.List;

public interface CatalogService {

    CreatedCatalogResponse createCatalog(CreateCatalogRequest request);
    Catalog getById(int id);
    List<GetListCatalogResponse> getAll();
}
