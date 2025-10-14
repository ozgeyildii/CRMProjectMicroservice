package com.etiya.customerservice.service.requests.city;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateCityRequest {
    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[a-zA-ZçÇşŞğĞıİüÜöÖ]+", message = "Name must contain only letters.")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreateCityRequest(String name) {
        this.name = name;
    }

    public CreateCityRequest() {
    }
}
