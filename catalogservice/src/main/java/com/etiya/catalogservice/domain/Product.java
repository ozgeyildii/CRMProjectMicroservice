package com.etiya.catalogservice.domain;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String id;

        private String name;

        private double price;

        private int stock;

        @ManyToOne
        @JoinColumn(name = "catalog_id", nullable = false)
        private Catalog catalog;

        @ManyToOne
        @JoinColumn(name = "spec_id", nullable = false)
        private ProductSpecification productSpecification;

        @OneToMany(mappedBy = "product")
        private List<ProductOffer> productOffers = new ArrayList<>();

        @OneToMany(mappedBy = "product")
        private List<ProdCharValue> prodCharValues = new ArrayList<>();

        @OneToMany(mappedBy = "product")
        private List<CampaignProduct> campaignProducts = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public ProductSpecification getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(ProductSpecification productSpecification) {
        this.productSpecification = productSpecification;
    }

    public List<ProductOffer> getProductOffers() {
        return productOffers;
    }

    public void setProductOffers(List<ProductOffer> productOffers) {
        this.productOffers = productOffers;
    }

    public List<ProdCharValue> getProdCharValues() {
        return prodCharValues;
    }

    public void setProdCharValues(List<ProdCharValue> prodCharValues) {
        this.prodCharValues = prodCharValues;
    }

    public List<CampaignProduct> getCampaignProducts() {
        return campaignProducts;
    }

    public void setCampaignProducts(List<CampaignProduct> campaignProducts) {
        this.campaignProducts = campaignProducts;
    }

    public Product() {
    }

    public Product(String id, String name, double price, int stock, Catalog catalog, ProductSpecification productSpecification, List<ProductOffer> productOffers, List<ProdCharValue> prodCharValues, List<CampaignProduct> campaignProducts) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.catalog = catalog;
        this.productSpecification = productSpecification;
        this.productOffers = productOffers;
        this.prodCharValues = prodCharValues;
        this.campaignProducts = campaignProducts;
    }
}