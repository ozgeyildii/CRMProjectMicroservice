package com.etiya.catalogservice.domain;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double price;

    private int billingAccountId;

    @ManyToOne
    @JoinColumn(name = "product_offer_id", nullable = false)
    private ProductOffer productOffer;


    public Product() {
    }

    public Product(String name, double price, int billingAccountId, ProductOffer productOffer) {
        this.name = name;
        this.price = price;
        this.billingAccountId = billingAccountId;
        this.productOffer = productOffer;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBillingAccountId() {
        return billingAccountId;
    }

    public void setBillingAccountId(int billingAccountId) {
        this.billingAccountId = billingAccountId;
    }

    public ProductOffer getProductOffer() {
        return productOffer;
    }

    public void setProductOffer(ProductOffer productOffer) {
        this.productOffer = productOffer;
    }
}