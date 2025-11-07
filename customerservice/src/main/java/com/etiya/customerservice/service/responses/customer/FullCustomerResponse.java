package com.etiya.customerservice.service.responses.customer;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class FullCustomerResponse {
    private UUID id;
    private String customerNumber;
    private String firstName;
    private String lastName;
    private String nationalId;
    private LocalDate dateOfBirth;
    private String motherName;
    private String fatherName;
    private String gender;

    private List<AddressResponse> addresses;
    private List<ContactMediumResponse> contactMediums;

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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
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

    public List<AddressResponse> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressResponse> addresses) {
        this.addresses = addresses;
    }

    public List<ContactMediumResponse> getContactMediums() {
        return contactMediums;
    }

    public void setContactMediums(List<ContactMediumResponse> contactMediums) {
        this.contactMediums = contactMediums;
    }

    public FullCustomerResponse() {
    }

    public FullCustomerResponse(UUID id, String customerNumber, String firstName, String lastName, String nationalId, LocalDate dateOfBirth, String motherName, String fatherName, String gender, List<AddressResponse> addresses, List<ContactMediumResponse> contactMediums) {
        this.id = id;
        this.customerNumber = customerNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.dateOfBirth = dateOfBirth;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.gender = gender;
        this.addresses = addresses;
        this.contactMediums = contactMediums;
    }
}
