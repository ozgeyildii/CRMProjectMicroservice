package com.etiya.customerservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "customer_number")
    private String customerNumber;

    //navigation property

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer",cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    private List<ContactMedium> contactMediums;

    //Getter-Setter

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<ContactMedium> getContactMediums() {
        return contactMediums;
    }

    public void setContactMediums(List<ContactMedium> contactMediums) {
        this.contactMediums = contactMediums;
    }

    public Customer(UUID id, String customerNumber) {
        this.id = id;
        this.customerNumber = customerNumber;
    }

    public Customer() {
    }

    @PrePersist
    public void generateCustomerNumber(){
        String prefix = "CUST-";
        String year = String.valueOf(java.time.Year.now().getValue());
        String randomPart = String.format("%07d",new java.util.Random().nextInt(10000000));
        this.customerNumber = prefix + year + "-" + randomPart;
    }
}


//Customerserviste => Address,City,District,BillingAccount,ContactMedium entitylerini ekleyelim.
//repositorylerini oluşturalım. Servis sınıflarını yazalım. Mapper sınıflarını yazalım. Global ex handler ekleyelim.
//Kafka nedir? araştıralım. ElasticSearch nedir? ConfigServer nedir ?