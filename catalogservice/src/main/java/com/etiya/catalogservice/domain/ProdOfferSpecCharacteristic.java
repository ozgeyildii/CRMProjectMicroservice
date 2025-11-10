package com.etiya.catalogservice.domain;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "product_offer_spec_characteristics")
public class ProdOfferSpecCharacteristic extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "is_required")
    private boolean isRequired;

    @ManyToOne
    @JoinColumn(name = "product_offer_spec_id", nullable = false)
    private ProductOfferSpecification productOfferSpecification;

    @ManyToOne
    @JoinColumn(name = "characteristic_id", nullable = false)
    private Characteristic characteristic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public ProductOfferSpecification getProductOfferSpecification() {
        return productOfferSpecification;
    }

    public void setProductOfferSpecification(ProductOfferSpecification productOfferSpecification) {
        this.productOfferSpecification = productOfferSpecification;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }
}