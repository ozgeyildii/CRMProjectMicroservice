package com.etiya.catalogservice.domain;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_offer_specifications")
public class ProductOfferSpecification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;

    @Column(name = "lifecycle_status")
    private String lifecycleStatus;

    @Column(name = "product_type")
    private String productType;

    @OneToMany(mappedBy = "productOfferSpecification")
    private List<ProductOffer> productOffers = new ArrayList<>();

    @OneToMany(mappedBy = "productOfferSpecification")
    private List<ProdOfferSpecCharacteristic> prodOfferSpecCharacteristics = new ArrayList<>();

    public ProductOfferSpecification() {
    }

    public ProductOfferSpecification(int id, String name, String description, String lifecycleStatus, String productType, List<ProductOffer> productOffers, List<ProdOfferSpecCharacteristic> prodOfferSpecCharacteristics) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lifecycleStatus = lifecycleStatus;
        this.productType = productType;
        this.productOffers = productOffers;
        this.prodOfferSpecCharacteristics = prodOfferSpecCharacteristics;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLifecycleStatus() {
        return lifecycleStatus;
    }

    public void setLifecycleStatus(String lifecycleStatus) {
        this.lifecycleStatus = lifecycleStatus;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public List<ProductOffer> getProductOffers() {
        return productOffers;
    }

    public void setProductOffers(List<ProductOffer> productOffers) {
        this.productOffers = productOffers;
    }

    public List<ProdOfferSpecCharacteristic> getProdOfferSpecCharacteristics() {
        return prodOfferSpecCharacteristics;
    }

    public void setProdOfferSpecCharacteristics(List<ProdOfferSpecCharacteristic> prodOfferSpecCharacteristics) {
        this.prodOfferSpecCharacteristics = prodOfferSpecCharacteristics;
    }
}