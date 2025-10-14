package com.etiya.customerservice.service.rules;


import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.repository.AddressRepository;
import org.springframework.stereotype.Service;

;

@Service
public class AddressBusinessRules {
    private final AddressRepository addressRepository;
    public AddressBusinessRules(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

//    public void checkIfAddressExists(int id) {
//        if(addressRepository.existsById(id)){
//            throw new BusinessException("Address with id " + id + " doesnt exists");
//        }
//    }

    public void checkIfBillingAccountExists(int id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new BusinessException("Address with id " + id + " does not exist"));
        if (!address.billingAccounts.isEmpty()) {
            throw new BusinessException("This address has a billing account");
        }
    }
}


