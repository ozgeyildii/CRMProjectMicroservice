package com.etiya.common.events;

import java.util.UUID;

public record UpdateAddressEvent(int id, String street, String houseNumber, String description, boolean isDefault, int districtId, UUID customerId) {
}
