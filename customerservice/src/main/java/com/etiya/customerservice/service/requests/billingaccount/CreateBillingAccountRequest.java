package com.etiya.customerservice.service.requests.billingaccount;

import com.etiya.customerservice.domain.enums.BillingAccountType;
import jakarta.validation.constraints.*;

public class CreateBillingAccountRequest {


    @NotBlank(message = "{accountNameIsRequired}")
    @Size(min = 3,max = 100,message = "{accountNameLengthConstraint}")
    @Pattern(regexp = "^[a-zA-Z0-9şıüğöçŞİÜĞÖÇ -]+$", message = "{accountNameContentConstraint}")
    private String accountName;

    @NotNull(message = "{accountTypeIsRequired}")
    private BillingAccountType type;

    @NotNull(message = "{customerIdCannotBeNull}")
    @Positive(message = "{customerIdMustBePositive}")
    private int customerId;

    @NotNull(message = "{AddressIdCanNotBeNull}")
    @Positive(message = "{AddressIdMustBePositive}")
    private int addressId;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public BillingAccountType getType() {
        return type;
    }

    public void setType(BillingAccountType type) {
        this.type = type;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public CreateBillingAccountRequest(String accountName, BillingAccountType type, int customerId, int addressId) {
        this.accountName = accountName;
        this.type = type;
        this.customerId = customerId;
        this.addressId = addressId;
    }

    public CreateBillingAccountRequest() {
    }
}
