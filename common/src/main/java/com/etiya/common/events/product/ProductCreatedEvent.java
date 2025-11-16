package com.etiya.common.events.product;

public record ProductCreatedEvent(String orderItemId,int productId, String productName, Integer campaignId, String campaignName) {
}
