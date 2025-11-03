package com.etiya.catalogservice.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity

@Table(name = "characteristics")

public class Characteristic {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    private String description;

    @Column(name = "data_type")

    private String dataType;

    @Column(name = "unit_of_measure")

    private String unitOfMeasure;

    @OneToMany(mappedBy = "characteristic")

    private List<CharacteristicValue> characteristicValues = new ArrayList<>();

    @OneToMany(mappedBy = "characteristic")

    private List<ProdSpecCharacteristic> prodSpecCharacteristics = new ArrayList<>();

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

    public List<ProdSpecCharacteristic> getProdSpecCharacteristics() {
        return prodSpecCharacteristics;
    }

    public void setProdSpecCharacteristics(List<ProdSpecCharacteristic> prodSpecCharacteristics) {
        this.prodSpecCharacteristics = prodSpecCharacteristics;
    }
}




