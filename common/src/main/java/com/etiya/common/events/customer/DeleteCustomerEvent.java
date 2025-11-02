package com.etiya.common.events.customer;

import java.util.UUID;

public record DeleteCustomerEvent(UUID customerId) {
}
