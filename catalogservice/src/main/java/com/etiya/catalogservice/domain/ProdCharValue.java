package com.etiya.catalogservice.domain;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "prod_char_values")
public class ProdCharValue extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "char_value_id", nullable = false)
    private CharacteristicValue characteristicValue;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public ProdCharValue(int id, CharacteristicValue characteristicValue, Product product) {
        this.id = id;
        this.characteristicValue = characteristicValue;
        this.product = product;
    }
}