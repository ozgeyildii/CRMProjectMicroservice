package com.etiya.common.events.address;

import java.util.UUID;

public record DeleteAddressEvent(int id, UUID customerId) {
}
