package com.etiya.customerservice.service.rules;

import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.customerservice.repository.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class CityBusinessRules {
    private final CityRepository cityRepository;

    public CityBusinessRules(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void checkCityNameAlreadyExists(String name){
        if (cityRepository.existsByNameIgnoreCase(name)){
            throw new BusinessException("City already exists");
        }
    }

    public void checkIfCityExistsById(int id){
        if (!cityRepository.existsById(id)){
            throw new BusinessException("City does not exist");
        }}
}
