package com.etiya.customerservice.service.requests.individualcustomers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateIndividualCustomerRequest {

    @NotBlank(message = "{firstNameIsRequired}")
    @Size(min = 2,max = 50,message = "{firstNameLengthConstraint}")
    private String firstName;

    private String lastName;

    private String middleName;

    @NotBlank(message = "{natIdIsRequired}")
    @Size(min = 11,max = 11,message = "{natIdLengthConstraint}")
    @Pattern(regexp = "^[0-9]+$",message = "{natIdContentConstraint}")
    private String nationalId;

    private LocalDate dateOfBirth;

    private String motherName;

    private String fatherName;

    private String gender;

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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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
}
