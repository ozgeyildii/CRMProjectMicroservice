package com.etiya.catalogservice.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "catalogs")
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Catalog parent;

    @OneToMany(mappedBy = "parent")
    private List<Catalog> subCatalogs = new ArrayList<>();

    @OneToMany(mappedBy = "catalog")
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "catalog")
    private List<CatalogProductOffer> catalogProductOffers = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Catalog getParent() {
        return parent;
    }

    public void setParent(Catalog parent) {
        this.parent = parent;
    }

    public List<Catalog> getSubCatalogs() {
        return subCatalogs;
    }

    public void setSubCatalogs(List<Catalog> subCatalogs) {
        this.subCatalogs = subCatalogs;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<CatalogProductOffer> getCatalogProductOffers() {
        return catalogProductOffers;
    }

    public void setCatalogProductOffers(List<CatalogProductOffer> catalogProductOffers) {
        this.catalogProductOffers = catalogProductOffers;
    }

    public Catalog() {
    }

    public Catalog(Long id, String name, Catalog parent, List<Catalog> subCatalogs, List<Product> products, List<CatalogProductOffer> catalogProductOffers) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.subCatalogs = subCatalogs;
        this.products = products;
        this.catalogProductOffers = catalogProductOffers;
    }
}
