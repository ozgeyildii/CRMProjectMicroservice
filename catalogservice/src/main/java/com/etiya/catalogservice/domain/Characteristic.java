package com.etiya.catalogservice.domain;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity

@Table(name = "characteristics")

public class Characteristic extends BaseEntity {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String name;

    private String description;

    @Column(name = "data_type")

    private String dataType;

    @Column(name = "unit_of_measure")

    private String unitOfMeasure;

    @OneToMany(mappedBy = "characteristic")

    private List<CharacteristicValue> characteristicValues = new ArrayList<>();

    @OneToMany(mappedBy = "characteristic")

    private List<ProdOfferSpecCharacteristic> prodOfferSpecCharacteristics = new ArrayList<>();

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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public List<CharacteristicValue> getCharacteristicValues() {
        return characteristicValues;
    }

    public void setCharacteristicValues(List<CharacteristicValue> characteristicValues) {
        this.characteristicValues = characteristicValues;
    }

    public List<ProdOfferSpecCharacteristic> getProdSpecCharacteristics() {
        return prodOfferSpecCharacteristics;
    }

    public void setProdSpecCharacteristics(List<ProdOfferSpecCharacteristic> prodOfferSpecCharacteristics) {
        this.prodOfferSpecCharacteristics = prodOfferSpecCharacteristics;
    }

    public Characteristic() {
    }

    public Characteristic(int id, String name, String description, String dataType, String unitOfMeasure, List<CharacteristicValue> characteristicValues, List<ProdOfferSpecCharacteristic> prodOfferSpecCharacteristics) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dataType = dataType;
        this.unitOfMeasure = unitOfMeasure;
        this.characteristicValues = characteristicValues;
        this.prodOfferSpecCharacteristics = prodOfferSpecCharacteristics;
    }
}




