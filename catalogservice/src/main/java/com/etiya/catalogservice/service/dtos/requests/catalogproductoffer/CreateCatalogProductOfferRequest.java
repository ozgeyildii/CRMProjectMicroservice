package com.etiya.catalogservice.service.dtos.requests.catalogproductoffer;

public class CreateCatalogProductOfferRequest {
    private int catalogId;
    private int productOfferId;

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(int productOfferId) {
        this.productOfferId = productOfferId;
    }
}