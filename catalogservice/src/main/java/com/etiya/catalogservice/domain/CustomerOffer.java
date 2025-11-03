package com.etiya.catalogservice.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "customer_offers")
public class CustomerOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId; // CustomerService referansı

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    private String status;

    @ManyToOne
    @JoinColumn(name = "product_offer_id", nullable = false)
    private ProductOffer productOffer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProductOffer getProductOffer() {
        return productOffer;
    }

    public void setProductOffer(ProductOffer productOffer) {
        this.productOffer = productOffer;
    }

    public CustomerOffer() {
    }

    public CustomerOffer(Long id, Long customerId, LocalDate expirationDate, String status, ProductOffer productOffer) {
        this.id = id;
        this.customerId = customerId;
        this.expirationDate = expirationDate;
        this.status = status;
        this.productOffer = productOffer;
    }
}