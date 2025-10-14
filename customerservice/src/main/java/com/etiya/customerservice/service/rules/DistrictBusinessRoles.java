package com.etiya.customerservice.service.rules;

import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.common.localization.LocalizationService;
import com.etiya.customerservice.domain.entities.District;
import com.etiya.customerservice.repository.DistrictRepository;
import com.etiya.customerservice.service.messages.Messages;
import org.springframework.stereotype.Service;

@Service
public class DistrictBusinessRoles {
    private final DistrictRepository districtRepository;
    private final LocalizationService localizationService;

    public DistrictBusinessRoles(DistrictRepository districtRepository, LocalizationService localizationService) {
        this.districtRepository = districtRepository;
        this.localizationService = localizationService;
    }

    public void checkIfAddressExists(int id){
        District district = districtRepository.findById(id).orElseThrow(() -> new BusinessException(localizationService.getMessage(Messages.DistrictNotExist)));
        if (!district.getAddresses().isEmpty()){
            throw new BusinessException(localizationService.getMessage(Messages.DistrictCannotDeleted));
        }
    }

    public void checkIfCityExists(int id){

    }
}
