package com.etiya.customerservice.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "corporate_customers")
@PrimaryKeyJoinColumn(name = "customer_id")
public class CorporateCustomer extends Customer {

    @OneToOne
    @JoinColumn(name = "id")
    private Customer customer;

    @Column(name = "companyName", nullable = false, length = 100)
    private String companyName;

    @Column(name = "taxNumber", unique = true, nullable = false, length = 10)
    private String taxNumber;

    @Column(name = "companyType")
    private String companyType;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public CorporateCustomer(int id, String customerNumber, Customer customer, String companyName, String taxNumber, String companyType) {
        super(id, customerNumber);
        this.customer = customer;
        this.companyName = companyName;
        this.taxNumber = taxNumber;
        this.companyType = companyType;
    }

    public CorporateCustomer() {
    }
}
