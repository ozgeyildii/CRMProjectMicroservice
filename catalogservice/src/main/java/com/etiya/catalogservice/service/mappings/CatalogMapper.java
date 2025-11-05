package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.Catalog;
import com.etiya.catalogservice.service.dtos.requests.catalog.CreateCatalogRequest;
import com.etiya.catalogservice.service.dtos.responses.catalog.CreatedCatalogResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CatalogMapper {

    CatalogMapper INSTANCE = Mappers.getMapper(CatalogMapper.class);

    Catalog catalogFromCreateCatalogRequest(CreateCatalogRequest request);


    CreatedCatalogResponse createdCatalogResponseFromCatalog(Catalog catalog);
}

