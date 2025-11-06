package com.etiya.common.events.address;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;
@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateAddressEvent(
        int id,
        String street,
        String houseNumber,
        String description,
        boolean isDefault,
        int districtId,
        String districtName,
        int cityId,
        String cityName,
        UUID customerId
) {}
