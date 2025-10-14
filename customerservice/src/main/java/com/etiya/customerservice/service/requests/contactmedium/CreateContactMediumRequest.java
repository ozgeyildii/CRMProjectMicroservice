package com.etiya.customerservice.service.requests.contactmedium;

import com.etiya.common.validators.annotations.ContactFormat;
import com.etiya.common.validators.annotations.EnumValidator;
import com.etiya.customerservice.domain.enums.ContactMediumType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@ContactFormat( // 🎯 İŞTE YENİ ANOTASYONU BURADA KULLANIYORUZ
        typeField = "type",
        valueField = "value",
        message = "{notCorrectCommunicationFormat}"
)
public class CreateContactMediumRequest {
    @NotBlank(message = "{typeCannotBeEmpty}")
    @EnumValidator(
            enumClass = ContactMediumType.class,
            message = "{invalidContactType}"
    )
    private String type;

    @NotBlank(message = "{valueCannotBeEmpty}")
    @Size(max = 150, message = "{valueLengthConstraint}")
    private String value;
    @NotNull(message = "{isPrimaryCannotBeEmpty}")
    private boolean isPrimary;
    @NotNull(message = "{customerIdCannotBeNull}")
    @Positive(message = "{customerIdMustBePositive}")
    private Integer customerId;

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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public CreateContactMediumRequest(String type, String value, boolean isPrimary, Integer customerId) {
        this.type = type;
        this.value = value;
        this.isPrimary = isPrimary;
        this.customerId = customerId;
    }

    public CreateContactMediumRequest() {
    }
}
