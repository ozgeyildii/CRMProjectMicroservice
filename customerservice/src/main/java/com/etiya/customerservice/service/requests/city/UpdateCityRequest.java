package com.etiya.customerservice.service.requests.city;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;



public class UpdateCityRequest {

    private int id;
    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[a-zA-ZçÇşŞğĞıİüÜöÖ]+", message = "Name must contain only letters.")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UpdateCityRequest(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public UpdateCityRequest() {
    }
}
