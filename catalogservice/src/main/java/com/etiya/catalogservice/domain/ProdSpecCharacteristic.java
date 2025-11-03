package com.etiya.catalogservice.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "product_spec_characteristics")
public class ProdSpecCharacteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_required")
    private Boolean isRequired;

    @ManyToOne
    @JoinColumn(name = "spec_id", nullable = false)
    private ProductSpecification productSpecification;

    @ManyToOne
    @JoinColumn(name = "characteristic_id", nullable = false)
    private Characteristic characteristic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getRequired() {
        return isRequired;
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }

    public ProductSpecification getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(ProductSpecification productSpecification) {
        this.productSpecification = productSpecification;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    public ProdSpecCharacteristic() {
    }

    public ProdSpecCharacteristic(Long id, Boolean isRequired, ProductSpecification productSpecification, Characteristic characteristic) {
        this.id = id;
        this.isRequired = isRequired;
        this.productSpecification = productSpecification;
        this.characteristic = characteristic;
    }
}