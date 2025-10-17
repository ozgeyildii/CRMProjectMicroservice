package com.etiya.common.events.contactmedium;

import java.util.UUID;

public record SoftDeleteContactMediumEvent(int id, UUID customerId, String deletedDate) {
}
