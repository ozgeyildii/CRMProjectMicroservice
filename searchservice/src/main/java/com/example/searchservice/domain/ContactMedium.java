package com.example.searchservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactMedium {

    private int id;
    private String type;
    private String value;
    @JsonProperty("isPrimary")
    private boolean primaryValue;
    private String customerId;
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

    public boolean isPrimaryValue() {
        return primaryValue;
    }

    public void setPrimaryValue(boolean primaryValue) {
        this.primaryValue = primaryValue;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(String deletedDate) {
        this.deletedDate = deletedDate;
    }

    public ContactMedium(int id, String type, String value, boolean primaryValue, String customerId, String deletedDate) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.primaryValue = primaryValue;
        this.customerId = customerId;
        this.deletedDate = deletedDate;
    }

    public ContactMedium(int id, String type, String value, boolean primaryValue, String customerId) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.primaryValue = primaryValue;
        this.customerId = customerId;
    }

    public ContactMedium() {
    }
}
