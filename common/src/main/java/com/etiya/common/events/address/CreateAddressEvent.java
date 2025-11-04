package com.etiya.common.events.address;

import java.util.UUID;

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
