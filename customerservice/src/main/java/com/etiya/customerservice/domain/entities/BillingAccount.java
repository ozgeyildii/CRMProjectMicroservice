package com.etiya.customerservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import com.etiya.customerservice.domain.enums.BillingAccountStatus;
import com.etiya.customerservice.domain.enums.BillingAccountType;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "billing_accounts") //-> billing_account
@SQLRestriction("deleted_date IS NULL")

public class BillingAccount extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private BillingAccountType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BillingAccountStatus status;

    @Column(name = "account_number", unique = true)
    private String accountNumber;


    @Column(name = "account_name")
    private String accountName;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @PrePersist
    public void generateAccountNumber() {
        String prefix = "BILL-";
        String year = String.valueOf(java.time.Year.now().getValue());
        String randomPart = String.format("%04d", new java.util.Random().nextInt(10000));
        this.accountNumber = prefix + year + "-" + randomPart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BillingAccountType getType() {
        return type;
    }

    public void setType(BillingAccountType type) {
        this.type = type;
    }

    public BillingAccountStatus getStatus() {
        return status;
    }

    public void setStatus(BillingAccountStatus status) {
        this.status = status;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BillingAccount(int id, BillingAccountType type, BillingAccountStatus status, String accountNumber, String accountName, Customer customer, Address address) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.customer = customer;
        this.address = address;
    }

    public BillingAccount() {
    }
}
