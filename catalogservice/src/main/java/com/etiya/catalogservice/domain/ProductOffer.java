package com.etiya.catalogservice.domain;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_offers")
public class ProductOffer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;


    @Column(name = "status")
    private String status;

    @Column(name="stock")
    private int stock;

    @Column(name="price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToMany(mappedBy = "productOffer")
    private List<CatalogProductOffer> catalogProductOffers = new ArrayList<>();

    @OneToMany(mappedBy = "productOffer")
    private List<CustomerOffer> customerOffers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "product_offer_spec_id", nullable = false)
    private ProductOfferSpecification productOfferSpecification;

    @OneToMany(mappedBy = "productOffer")
    private List<ProdOfferCharValue> prodOfferCharValues = new ArrayList<>();

    @OneToMany(mappedBy = "productOffer")
    private List<CampaignProductOffer> campaignProductOffers = new ArrayList<>();

    public ProductOffer() {
    }

    public ProductOffer(int stock, int id, String name, String description, LocalDate startDate, LocalDate endDate, String status, BigDecimal price, Product product, List<CatalogProductOffer> catalogProductOffers, List<CustomerOffer> customerOffers, ProductOfferSpecification productOfferSpecification, List<ProdOfferCharValue> prodOfferCharValues, List<CampaignProductOffer> campaignProductOffers) {
        this.stock = stock;
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.price = price;
        this.product = product;
        this.catalogProductOffers = catalogProductOffers;
        this.customerOffers = customerOffers;
        this.productOfferSpecification = productOfferSpecification;
        this.prodOfferCharValues = prodOfferCharValues;
        this.campaignProductOffers = campaignProductOffers;
    }

    public List<CampaignProductOffer> getCampaignProductOffers() {
        return campaignProductOffers;
    }

    public void setCampaignProductOffers(List<CampaignProductOffer> campaignProductOffers) {
        this.campaignProductOffers = campaignProductOffers;
    }

    public List<ProdOfferCharValue> getProdOfferCharValues() {
        return prodOfferCharValues;
    }

    public void setProdOfferCharValues(List<ProdOfferCharValue> prodOfferCharValues) {
        this.prodOfferCharValues = prodOfferCharValues;
    }

    public ProductOfferSpecification getProductOfferSpecification() {
        return productOfferSpecification;
    }

    public void setProductOfferSpecification(ProductOfferSpecification productOfferSpecification) {
        this.productOfferSpecification = productOfferSpecification;
    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}