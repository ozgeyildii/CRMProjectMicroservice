package com.etiya.common.events.contactmedium;

import java.util.UUID;

public record CreateContactMediumEvent(int id, String type, String value, boolean isPrimary, UUID customerId) {
}
