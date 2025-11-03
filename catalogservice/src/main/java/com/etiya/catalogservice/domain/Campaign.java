package com.etiya.catalogservice.domain;
import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "campaigns")
public class Campaign extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "campaign_code", unique = true)
    private String campaignCode;

    @Column(name = "discount_rate")
    private BigDecimal discountRate;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    private List<CampaignProduct> campaignProducts = new ArrayList<>();

    public Campaign() {
    }

    public Campaign(Long id, String name, LocalDate startDate, LocalDate endDate, String campaignCode, BigDecimal discountRate, List<CampaignProduct> campaignProducts) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.campaignCode = campaignCode;
        this.discountRate = discountRate;
        this.campaignProducts = campaignProducts;
    }

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

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public List<CampaignProduct> getCampaignProducts() {
        return campaignProducts;
    }

    public void setCampaignProducts(List<CampaignProduct> campaignProducts) {
        this.campaignProducts = campaignProducts;
    }


}
