package com.etiya.common.events.product;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record CreateProductEvent(String name,double price,int billingAccountId, int product_offer_id) {
}
