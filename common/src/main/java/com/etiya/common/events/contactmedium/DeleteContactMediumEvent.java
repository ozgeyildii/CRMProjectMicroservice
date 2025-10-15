package com.etiya.common.events.contactmedium;

import java.util.UUID;

public record DeleteContactMediumEvent(int id, UUID customerId) {
}
