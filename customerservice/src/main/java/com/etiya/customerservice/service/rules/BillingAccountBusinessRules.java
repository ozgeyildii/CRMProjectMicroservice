package com.etiya.customerservice.service.rules;

import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.domain.entities.BillingAccount;
import com.etiya.customerservice.domain.enums.BillingAccountStatus;
import com.etiya.customerservice.domain.enums.BillingAccountType;
import com.etiya.customerservice.repository.AddressRepository;
import com.etiya.customerservice.repository.BillingAccountRepository;
import com.etiya.customerservice.service.abstracts.CustomerService;
import org.springframework.stereotype.Service;


@Service
public class BillingAccountBusinessRules {
    private final BillingAccountRepository billingAccountRepository;
    private final AddressRepository addressRepository;
    private final CustomerService customerService;  // ← EKLE!


    public BillingAccountBusinessRules(BillingAccountRepository billingAccountRepository, AddressRepository addressRepository, CustomerService customerService) {
        this.billingAccountRepository = billingAccountRepository;
        this.addressRepository = addressRepository;
        this.customerService = customerService;
    }

    public void checkIfBillingAccountCanBeDeleted(int id){
        BillingAccount billingAccount = billingAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("Billing account with id " + id + " does not exist"));
        if(billingAccount.getStatus() == BillingAccountStatus.ACTIVE){
            throw new BusinessException("Active billing account cannot be deleted");
        }
    }

    public void checkIfTypeCanBeChanged(int id, BillingAccountType newType){
        BillingAccount existingBillingAccount = billingAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("Billing account with id " + id + " does not exist"));

        if(existingBillingAccount.getType() != newType){
            throw new BusinessException("Billing account type cannot be changed");
        }
    }

    public void checkIfAddressBelongsToCustomer(int addressId, int customerId){
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new BusinessException("Address with id " + addressId + " does not exist"));
        if(address.getCustomer().getId() != customerId){
            throw new BusinessException("Address does not belong to the specified customer");
        }
    }

    public void checkIfStatusTransitionIsValid(int id, BillingAccountStatus newStatus){
       BillingAccount existingBillingAccount = billingAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("Billing account with id " + id + " does not exist"));
         BillingAccountStatus currentStatus = existingBillingAccount.getStatus();

         if(currentStatus == newStatus){
                return; // No change in status
         }

         if(currentStatus == BillingAccountStatus.CLOSED){
                throw new BusinessException("Cannot change status of a closed billing account");
         }

    }

    public void checkIfCustomerTypeMatchesAccountType(int customerId, BillingAccountType accountType) {
        String customerType = customerService.getCustomerType(customerId);

        if (customerType.equals("INDIVIDUAL") && accountType != BillingAccountType.INDIVIDUAL) {
            throw new BusinessException(
                    "Individual customers can only have INDIVIDUAL billing accounts"
            );
        }

        if (customerType.equals("CORPORATE") && accountType != BillingAccountType.CORPORATE) {
            throw new BusinessException(
                    "Corporate customers can only have CORPORATE billing accounts"
            );
        }
    }


    public void checkIfCustomerHasAddress(int customerId) {
        if (!addressRepository.existsByCustomerId(customerId)) {
            throw new BusinessException(
                    "Customer must have at least one address before opening a billing account"
            );
        }
    }
}
