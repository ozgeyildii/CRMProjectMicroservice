package com.etiya.common.events;

import java.util.UUID;

public record DeleteAddressEvent(int id, UUID customerId) {
}
