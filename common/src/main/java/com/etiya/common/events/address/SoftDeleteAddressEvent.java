package com.etiya.common.events.address;

import java.util.UUID;

public record SoftDeleteAddressEvent(int id, UUID customerId, String deletedDate) {
}
