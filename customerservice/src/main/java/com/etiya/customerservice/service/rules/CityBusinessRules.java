package com.etiya.customerservice.service.rules;

import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.common.localization.LocalizationService;
import com.etiya.customerservice.repository.CityRepository;
import com.etiya.customerservice.service.messages.Messages;
import org.springframework.stereotype.Service;

@Service
public class CityBusinessRules {
    private final CityRepository cityRepository;
    private final LocalizationService localizationService;

    public CityBusinessRules(CityRepository cityRepository, LocalizationService localizationService) {
        this.cityRepository = cityRepository;
        this.localizationService = localizationService;
    }

    public void checkCityNameAlreadyExists(String name){
        if (cityRepository.existsByNameIgnoreCase(name)){
            throw new BusinessException(localizationService.getMessage(Messages.CityExist));
        }
    }

    public void checkIfCityExistsById(int id){
        if (!cityRepository.existsById(id)){
            throw new BusinessException(localizationService.getMessage(Messages.CityNotExist));
        }}
}
