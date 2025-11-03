package com.etiya.catalogservice.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "campaign_products")
public class CampaignProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    public CampaignProduct() {
    }

    public CampaignProduct(Long id, Product product, Campaign campaign) {
        this.id = id;
        this.product = product;
        this.campaign = campaign;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
}