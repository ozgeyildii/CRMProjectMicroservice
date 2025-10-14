package com.etiya.customerservice.service.requests.city;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateCityRequest {
    @NotBlank(message = "{nameIsRequired}")
    @Pattern(regexp = "^[a-zA-ZçÇşŞğĞıİüÜöÖ]+", message = "{nameMustContainOnlyLetters}")
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
