package com.example.searchservice.domain;

import org.springframework.data.elasticsearch.annotations.FieldType;
import jakarta.persistence.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;


import java.util.ArrayList;
import java.util.List;

@Document(indexName = "customer-search", createIndex = true)
public class CustomerSearch {

    @Id
    private String id;

    private String customerNumber;
    private String firstName;

    private String lastName;

    private String nationalId;

    private String dateOfBirth;

    private String motherName;

    private String fatherName;

    private String gender;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Address> addresses=new ArrayList<>();

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<ContactMedium> contactMediums= new ArrayList<>();

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<BillingAccount> billingAccounts= new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public List<BillingAccount> getBillingAccounts() {
        return billingAccounts;
    }

    public void setBillingAccounts(List<BillingAccount> billingAccounts) {
        this.billingAccounts = billingAccounts;
    }

    public CustomerSearch(String id, String customerNumber, String firstName, String lastName, String nationalId, String dateOfBirth, String motherName, String fatherName, String gender) {
        this.id = id;
        this.customerNumber = customerNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.dateOfBirth = dateOfBirth;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.gender = gender;
    }

    public CustomerSearch(){}

}

