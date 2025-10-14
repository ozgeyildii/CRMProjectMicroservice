package com.etiya.customerservice.service.rules;

import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.customerservice.domain.entities.ContactMedium;
import com.etiya.customerservice.repository.ContactMediumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactMediumBusinessRules {
    private final ContactMediumRepository contactMediumRepository;


    public ContactMediumBusinessRules(ContactMediumRepository contactMediumRepository) {
        this.contactMediumRepository = contactMediumRepository;
    }

    public void checkIsPrimaryOnlyOne(ContactMedium contactMedium) {
        if (contactMedium.isPrimary()) {
            int ownerCustomerId = contactMedium.getCustomer().getId();
            List<ContactMedium> contactMediumList = contactMediumRepository.findByCustomerId(ownerCustomerId);
            if (!contactMediumList.isEmpty()) {
                for (ContactMedium cm : contactMediumList) {
                    cm.setPrimary(false);
                }
            }

        }
    }

    public void checkIsPrimary(ContactMedium contactMedium) {
        if (contactMedium.isPrimary()) {
            throw new BusinessException("Contact Medium is Primary. You can't delete your Primary Contact ");
        }
    }
}
