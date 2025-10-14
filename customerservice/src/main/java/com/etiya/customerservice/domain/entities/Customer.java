package com.etiya.customerservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "customer_number")
    private String customerNumber;


    //navigation property

   /* @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    private List<Address> addresses;*/

    //Getter-Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

 /*   public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }*/

    public Customer(int id, String customerNumber) {
        this.id = id;
        this.customerNumber = customerNumber;
    }

    public Customer() {
    }

    @PrePersist
    public void generateCustomerNumber(){
        String prefix = "CUST-";
        String year = String.valueOf(java.time.Year.now().getValue());
        String randomPart = String.format("%04d",new java.util.Random().nextInt(10000));
        this.customerNumber = prefix + year + "-" + randomPart;
    }
}


//Customerserviste => Address,City,District,BillingAccount,ContactMedium entitylerini ekleyelim.
//repositorylerini oluşturalım. Servis sınıflarını yazalım. Mapper sınıflarını yazalım. Global ex handler ekleyelim.
//Kafka nedir? araştıralım. ElasticSearch nedir? ConfigServer nedir ?