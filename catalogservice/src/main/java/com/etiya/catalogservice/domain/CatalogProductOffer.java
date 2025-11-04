package com.etiya.catalogservice.domain;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "catalog_product_offers")
public class CatalogProductOffer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_offer_id", nullable = false)
    private ProductOffer productOffer;

    @ManyToOne
    @JoinColumn(name = "catalog_id", nullable = false)
    private Catalog catalog;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductOffer getProductOffer() {
        return productOffer;
    }

    public void setProductOffer(ProductOffer productOffer) {
        this.productOffer = productOffer;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public CatalogProductOffer() {
    }

    public CatalogProductOffer(Long id, ProductOffer productOffer, Catalog catalog) {
        this.id = id;
        this.productOffer = productOffer;
        this.catalog = catalog;
    }
}