package com.etiya.customerservice.service.requests.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateAddressRequest {

    @NotBlank(message = "Street is required")
    private String street;
    @NotBlank(message = "House number is required")
    private String houseNumber;
    @Size(max = 255, message = "Description can't be longer than 255 characters")
    private String description;
    private boolean isDefault;
    private int districtId;
    private int customerId;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public CreateAddressRequest(String street, String houseNumber, String description, boolean isDefault, int districtId, int customerId) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.description = description;
        this.isDefault = isDefault;
        this.districtId = districtId;
        this.customerId = customerId;
    }

    public CreateAddressRequest() {
    }
}
