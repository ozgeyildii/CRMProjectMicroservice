package com.etiya.customerservice.service.requests.contactmedium;

import com.etiya.common.validators.annotations.ContactFormat;
import com.etiya.common.validators.annotations.EnumValidator;
import com.etiya.customerservice.domain.enums.ContactMediumType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@ContactFormat( // 🎯 İŞTE YENİ ANOTASYONU BURADA KULLANIYORUZ
        typeField = "type",
        valueField = "value",
        message = "Entered value is not in the correct format for the specified communication type."
)
public class CreateContactMediumRequest {
    @NotBlank(message = "Type couldn't be empty")
    @EnumValidator(
            enumClass = ContactMediumType.class,
            message = "Invalid contact type please choose one of these: EMAIL, PHONE, FAX, MOBILE"
    )
    private String type;

    @NotBlank
    @Size(max = 150, message = "Value can't be longer than 150 characters")
    private String value;
    @NotNull
    private boolean isPrimary;
    @NotNull
    @Positive(message = "CityId must be positive")
    private UUID customerId;

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

    public CreateContactMediumRequest(String type, String value, boolean isPrimary, UUID customerId) {
        this.type = type;
        this.value = value;
        this.isPrimary = isPrimary;
        this.customerId = customerId;
    }

    public CreateContactMediumRequest() {
    }
}
