package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.Catalog;
import com.etiya.catalogservice.repository.CatalogRepository;
import com.etiya.catalogservice.service.abstracts.CatalogService;
import com.etiya.catalogservice.service.dtos.requests.catalog.CreateCatalogRequest;
import com.etiya.catalogservice.service.dtos.responses.catalog.CreatedCatalogResponse;
import com.etiya.catalogservice.service.dtos.responses.catalog.GetListCatalogResponse;
import com.etiya.catalogservice.service.mappings.CatalogMapper;
import com.etiya.catalogservice.service.messages.Messages;
import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.common.localization.LocalizationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;
    private final LocalizationService  localizationService;

    public CatalogServiceImpl(CatalogRepository catalogRepository, LocalizationService localizationService) {
        this.catalogRepository = catalogRepository;
        this.localizationService = localizationService;
    }

    @Override
    public CreatedCatalogResponse createCatalog(CreateCatalogRequest request) {
        Catalog catalog = CatalogMapper.INSTANCE.catalogFromCreateCatalogRequest(request);

        Catalog parent = catalogRepository.findById(request.getParentId())
                .orElseThrow(() -> new BusinessException(localizationService.getMessage(Messages.ParentCatalogNotFound)));
        catalog.setParent(parent);


        catalog = catalogRepository.save(catalog);
        return CatalogMapper.INSTANCE.createdCatalogResponseFromCatalog(catalog);
    }

    @Override
    public Catalog getById(int id) {
        return catalogRepository.findById(id).orElseThrow(() -> new BusinessException(localizationService.getMessage(Messages.CatalogNotFound)));
    }

    @Override
    public List<GetListCatalogResponse> getAll() {
        List<Catalog> catalogs = catalogRepository.findAll();
        return CatalogMapper.INSTANCE.getListCatalogResponseFromCatalog(catalogs);
    }
}
