package com.etiya.catalogservice.domain;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String name;

        private double price;

        private int stock;

        @ManyToOne
        @JoinColumn(name = "catalog_id", nullable = false)
        private Catalog catalog;

//        @ManyToOne
//        @JoinColumn(name = "spec_id", nullable = false)
//        private ProductOfferSpecification productOfferSpecification;

        @OneToMany(mappedBy = "product")
        private List<ProductOffer> productOffers = new ArrayList<>();

//        @OneToMany(mappedBy = "product")
//        private List<ProdOfferCharValue> prodOfferCharValues = new ArrayList<>();
//
//        @OneToMany(mappedBy = "product")
//        private List<CampaignProductOffer> campaignProductOffers = new ArrayList<>();


    public Product() {
    }

    public Product(int id, String name, double price, int stock, Catalog catalog, List<ProductOffer> productOffers) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.catalog = catalog;
        this.productOffers = productOffers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<ProductOffer> getProductOffers() {
        return productOffers;
    }

    public void setProductOffers(List<ProductOffer> productOffers) {
        this.productOffers = productOffers;
    }
}