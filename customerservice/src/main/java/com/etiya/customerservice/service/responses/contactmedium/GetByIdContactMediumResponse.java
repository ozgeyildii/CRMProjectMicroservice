package com.etiya.customerservice.service.responses.contactmedium;

import com.etiya.customerservice.domain.enums.ContactMediumType;

public class GetByIdContactMediumResponse {
    private int id;
    private ContactMediumType type;
    private String value;
    private boolean isPrimary;
    private int customerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContactMediumType getType() {
        return type;
    }

    public void setType(ContactMediumType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public GetByIdContactMediumResponse(int id, ContactMediumType type, String value, boolean isPrimary, int customerId) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.isPrimary = isPrimary;
        this.customerId = customerId;
    }

    public GetByIdContactMediumResponse() {
    }
}
