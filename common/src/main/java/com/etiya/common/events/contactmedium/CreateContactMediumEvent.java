package com.etiya.common.events.contactmedium;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;
@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateContactMediumEvent(int id, String type, String value, boolean isPrimary, UUID customerId) {
}
