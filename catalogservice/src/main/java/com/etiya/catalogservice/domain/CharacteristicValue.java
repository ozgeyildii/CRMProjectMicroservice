package com.etiya.catalogservice.domain;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "char_values")
public class CharacteristicValue extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String value;

    @ManyToOne
    @JoinColumn(name = "char_id", nullable = false)
    private Characteristic characteristic;

    @OneToMany(mappedBy = "characteristicValue")
    private List<ProdOfferCharValue> prodOfferCharValues = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    public List<ProdOfferCharValue> getProdCharValues() {
        return prodOfferCharValues;
    }

    public void setProdCharValues(List<ProdOfferCharValue> prodOfferCharValues) {
        this.prodOfferCharValues = prodOfferCharValues;
    }

    public CharacteristicValue() {
    }

    public CharacteristicValue(int id, String value, Characteristic characteristic, List<ProdOfferCharValue> prodOfferCharValues) {
        this.id = id;
        this.value = value;
        this.characteristic = characteristic;
        this.prodOfferCharValues = prodOfferCharValues;
    }
}
