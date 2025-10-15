package com.etiya.customerservice.service.requests.district;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CreateDistrictRequest {

    @NotBlank(message = "{nameIsRequired}")
    @Size(max = 255, message = "{descriptionCannotBeLong}")
    private String name;

    @NotNull(message = "{cityIdIsRequired}")
    @Positive(message = "{cityIdMustBePositive}")
    private Integer cityId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public CreateDistrictRequest(String name, Integer cityId) {
        this.name = name;
        this.cityId = cityId;
    }

    public CreateDistrictRequest() {
    }
}
