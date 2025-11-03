package com.etiya.catalogservice.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "prod_char_values")
public class ProdCharValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "char_value_id", nullable = false)
    private CharacteristicValue characteristicValue;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CharacteristicValue getCharacteristicValue() {
        return characteristicValue;
    }

    public void setCharacteristicValue(CharacteristicValue characteristicValue) {
        this.characteristicValue = characteristicValue;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProdCharValue() {
    }

    public ProdCharValue(Long id, CharacteristicValue characteristicValue, Product product) {
        this.id = id;
        this.characteristicValue = characteristicValue;
        this.product = product;
    }
}