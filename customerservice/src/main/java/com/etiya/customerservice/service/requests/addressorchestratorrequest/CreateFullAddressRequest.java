package com.etiya.customerservice.service.requests.addressorchestratorrequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class CreateFullAddressRequest {
    @NotBlank(message = "{streetIsRequired}")
    private String street;

    @NotBlank(message = "{houseNumberIsRequired}")
    private String houseNumber;

    @Size(message = "{descriptionCannotBeLong}")
    private String description;

    private boolean isDefault;

    // Eğer FE isim gönderiyorsa bunları ekle
    @NotBlank(message = "{cityIsRequired}")
    private String city;

    @NotBlank(message = "{districtIsRequired}")
    private String district;

    private UUID customerId;

    // Getters & Setters
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getHouseNumber() { return houseNumber; }
    public void setHouseNumber(String houseNumber) { this.houseNumber = houseNumber; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isDefault() { return isDefault; }
    public void setDefault(boolean aDefault) { isDefault = aDefault; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public UUID getCustomerId() { return customerId; }
    public void setCustomerId(UUID customerId) { this.customerId = customerId; }
}