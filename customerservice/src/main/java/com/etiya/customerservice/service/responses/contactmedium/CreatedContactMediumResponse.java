package com.etiya.customerservice.service.responses.contactmedium;

import com.etiya.customerservice.domain.enums.ContactMediumType;

public class CreatedContactMediumResponse {
    private int id;
    private ContactMediumType type;
    private String value;
    private boolean isPrimary;

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

    public CreatedContactMediumResponse(int id, ContactMediumType type, String value, boolean isPrimary) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.isPrimary = isPrimary;
    }

    public CreatedContactMediumResponse() {
    }
}
