package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.Catalog;
import com.etiya.catalogservice.service.dtos.requests.catalog.CreateCatalogRequest;
import com.etiya.catalogservice.service.dtos.responses.catalog.CreatedCatalogResponse;
import com.etiya.catalogservice.service.dtos.responses.catalog.GetListCatalogResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CatalogMapper {

    CatalogMapper INSTANCE = Mappers.getMapper(CatalogMapper.class);

    Catalog catalogFromCreateCatalogRequest(CreateCatalogRequest request);


    CreatedCatalogResponse createdCatalogResponseFromCatalog(Catalog catalog);

    List<GetListCatalogResponse> getListCatalogResponseFromCatalog(List<Catalog> catalogs);
}

