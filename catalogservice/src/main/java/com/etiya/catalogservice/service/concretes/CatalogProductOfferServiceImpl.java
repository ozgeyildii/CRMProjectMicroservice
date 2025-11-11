package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.Catalog;
import com.etiya.catalogservice.domain.CatalogProductOffer;
import com.etiya.catalogservice.domain.ProductOffer;
import com.etiya.catalogservice.repository.CatalogProductOfferRepository;
import com.etiya.catalogservice.service.abstracts.CatalogProductOfferService;
import com.etiya.catalogservice.service.abstracts.CatalogService;
import com.etiya.catalogservice.service.abstracts.ProductOfferService;
import com.etiya.catalogservice.service.dtos.requests.catalogproductoffer.CreateCatalogProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.catalogproductoffer.CreatedCatalogProductOfferResponse;
import com.etiya.catalogservice.service.mappings.CatalogProductOfferMapper;
import com.etiya.common.responses.CampaignProductOfferResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CatalogProductOfferServiceImpl implements CatalogProductOfferService {

    private final CatalogProductOfferRepository catalogProductOfferRepository;
    private final ProductOfferService  productOfferService;
    private final CatalogService  catalogService;
    private final CatalogProductOfferRepository productOfferRepository;

    public CatalogProductOfferServiceImpl(
            CatalogProductOfferRepository catalogProductOfferRepository,
            ProductOfferService  productOfferService,
            CatalogService  catalogService, CatalogProductOfferRepository productOfferRepository

    ) {
        this.catalogProductOfferRepository = catalogProductOfferRepository;
        this.productOfferService = productOfferService;
        this.catalogService = catalogService;
        this.productOfferRepository = productOfferRepository;
    }

    @Transactional
    @Override
    public CreatedCatalogProductOfferResponse add(CreateCatalogProductOfferRequest request) {
        Catalog catalog = catalogService.getById(request.getCatalogId());

        ProductOffer productOffer = productOfferService.getByEntityId(request.getProductOfferId());

        CatalogProductOffer catalogProductOffer = new CatalogProductOffer();
        catalogProductOffer.setCatalog(catalog);
        catalogProductOffer.setProductOffer(productOffer);

        CatalogProductOffer saved = catalogProductOfferRepository.save(catalogProductOffer);
        return CatalogProductOfferMapper.INSTANCE.createdCatalogProductOfferResponseFromCatalogProductOffer(saved);
    }

    @Override
    public CampaignProductOfferResponse getById(int id) {

        return catalogProductOfferRepository.findCampaignOfferById(id);
    }
}