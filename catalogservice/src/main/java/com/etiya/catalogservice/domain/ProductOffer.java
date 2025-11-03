package com.etiya.catalogservice.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_offers")
public class ProductOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "discount_rate")
    private BigDecimal discountRate;

    private String status;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToMany(mappedBy = "productOffer")
    private List<CatalogProductOffer> catalogProductOffers = new ArrayList<>();

    @OneToMany(mappedBy = "productOffer")
    private List<CustomerOffer> customerOffers = new ArrayList<>();

    public List<CustomerOffer> getCustomerOffers() {
        return customerOffers;
    }

    public void setCustomerOffers(List<CustomerOffer> customerOffers) {
        this.customerOffers = customerOffers;
    }

    public List<CatalogProductOffer> getCatalogProductOffers() {
        return catalogProductOffers;
    }

    public void setCatalogProductOffers(List<CatalogProductOffer> catalogProductOffers) {
        this.catalogProductOffers = catalogProductOffers;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductOffer() {
    }

    public ProductOffer(Long id, String name, String description, LocalDate startDate, LocalDate endDate, BigDecimal discountRate, String status, Product product, List<CatalogProductOffer> catalogProductOffers, List<CustomerOffer> customerOffers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountRate = discountRate;
        this.status = status;
        this.product = product;
        this.catalogProductOffers = catalogProductOffers;
        this.customerOffers = customerOffers;
    }


}