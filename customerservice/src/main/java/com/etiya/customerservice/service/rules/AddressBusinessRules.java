package com.etiya.customerservice.service.rules;

import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.common.localization.LocalizationService;
import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.repository.AddressRepository;
import com.etiya.customerservice.service.messages.Messages;
import org.springframework.stereotype.Service;

@Service
public class AddressBusinessRules {
    private final AddressRepository addressRepository;
    private final LocalizationService localizationService;

    public AddressBusinessRules(AddressRepository addressRepository, LocalizationService localizationService) {
        this.addressRepository = addressRepository;
        this.localizationService = localizationService;
    }

    public Address getAddressOrThrowException(int id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        localizationService.getMessage(Messages.AddressNotExist)
                ));
    }


    public void checkIfBillingAccountExists(int id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new BusinessException(localizationService.getMessage(Messages.AddressNotExist)));
        if (!address.billingAccounts.isEmpty()) {
            throw new BusinessException(localizationService.getMessage(Messages.BillingAccountExists));
        }
    }

    public void checkIfPrimaryAddressExists(int id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new BusinessException(localizationService.getMessage(Messages.AddressNotExist)));
        if (address.isDefault()) {
            throw new BusinessException(localizationService.getMessage(Messages.PrimaryAddressCannotBeDeleted));
        }
    }


}


