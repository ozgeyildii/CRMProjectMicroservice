package com.etiya.catalogservice.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "char_values")
public class CharacteristicValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @ManyToOne
    @JoinColumn(name = "char_id", nullable = false)
    private Characteristic characteristic;

    @OneToMany(mappedBy = "characteristicValue")
    private List<ProdCharValue> prodCharValues = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<ProdCharValue> getProdCharValues() {
        return prodCharValues;
    }

    public void setProdCharValues(List<ProdCharValue> prodCharValues) {
        this.prodCharValues = prodCharValues;
    }

    public CharacteristicValue() {
    }

    public CharacteristicValue(Long id, String value, Characteristic characteristic, List<ProdCharValue> prodCharValues) {
        this.id = id;
        this.value = value;
        this.characteristic = characteristic;
        this.prodCharValues = prodCharValues;
    }
}
