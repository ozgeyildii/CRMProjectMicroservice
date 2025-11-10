package com.etiya.catalogservice.domain;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "prod_offer_char_values")
public class ProdOfferCharValue extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "char_value_id", nullable = false)
    private CharacteristicValue characteristicValue;

    @ManyToOne
    @JoinColumn(name = "product_offer_id", nullable = false)
    private ProductOffer productOffer;

    public ProdOfferCharValue() {
    }

    public ProdOfferCharValue(int id, CharacteristicValue characteristicValue, ProductOffer productOffer) {
        this.id = id;
        this.characteristicValue = characteristicValue;
        this.productOffer = productOffer;
    }

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

    public ProductOffer getProductOffer() {
        return productOffer;
    }

    public void setProductOffer(ProductOffer productOffer) {
        this.productOffer = productOffer;
    }
}