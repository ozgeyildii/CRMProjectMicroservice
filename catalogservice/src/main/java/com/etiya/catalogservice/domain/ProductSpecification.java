package com.etiya.catalogservice.domain;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_specifications")
public class ProductSpecification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;

    @Column(name = "lifecycle_status")
    private String lifecycleStatus;

    @Column(name = "product_type")
    private String productType;

    @OneToMany(mappedBy = "productSpecification")
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "productSpecification")
    private List<ProdSpecCharacteristic> prodSpecCharacteristics = new ArrayList<>();

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<ProdSpecCharacteristic> getProdSpecCharacteristics() {
        return prodSpecCharacteristics;
    }

    public void setProdSpecCharacteristics(List<ProdSpecCharacteristic> prodSpecCharacteristics) {
        this.prodSpecCharacteristics = prodSpecCharacteristics;
    }

    public ProductSpecification() {
    }

    public ProductSpecification(int id, String name, String description, String lifecycleStatus, String productType, List<Product> products, List<ProdSpecCharacteristic> prodSpecCharacteristics) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lifecycleStatus = lifecycleStatus;
        this.productType = productType;
        this.products = products;
        this.prodSpecCharacteristics = prodSpecCharacteristics;
    }
}