package com.example.searchservice.domain;

import java.util.UUID;

public class ContactMedium {

    private int id;
    private String type;
    private String value;
    private boolean isPrimary;

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


    public ContactMedium(int id, String type, String value, boolean isPrimary) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.isPrimary = isPrimary;
    }

    public ContactMedium() {
    }
}
