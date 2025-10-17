package com.example.searchservice.domain;

import java.util.UUID;

public class ContactMedium {

    private int id;
    private String type;
    private String value;
    private boolean isPrimary;
    private UUID customerId;
    private String deletedDate=null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(String deletedDate) {
        this.deletedDate = deletedDate;
    }

    public ContactMedium(int id, String type, String value, boolean isPrimary, UUID customerId, String deletedDate) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.isPrimary = isPrimary;
        this.customerId = customerId;
        this.deletedDate = deletedDate;
    }

    public ContactMedium(int id, String type, String value, boolean isPrimary, UUID customerId) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.isPrimary = isPrimary;
        this.customerId = customerId;
    }

    public ContactMedium() {
    }
}
