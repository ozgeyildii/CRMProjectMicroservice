package com.etiya.catalogservice.domain;
import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "campaigns")
public class Campaign extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "campaign_code", unique = true)
    private String campaignCode;

    @Column(name = "discount_rate")
    private double discountRate;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    private List<CampaignProductOffer> campaignProductOffers = new ArrayList<>();

    public Campaign() {
    }

    public Campaign(int id, String name, LocalDate startDate, LocalDate endDate, String campaignCode, double discountRate, List<CampaignProductOffer> campaignProductOffers) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.campaignCode = campaignCode;
        this.discountRate = discountRate;
        this.campaignProductOffers = campaignProductOffers;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getCampaignCode() {
        return campaignCode;
    }

    public void setCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public List<CampaignProductOffer> getCampaignProducts() {
        return campaignProductOffers;
    }

    public void setCampaignProducts(List<CampaignProductOffer> campaignProductOffers) {
        this.campaignProductOffers = campaignProductOffers;
    }
}
