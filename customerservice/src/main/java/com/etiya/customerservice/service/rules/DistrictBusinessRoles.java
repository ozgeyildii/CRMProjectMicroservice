package com.etiya.customerservice.service.rules;

import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.customerservice.domain.entities.District;
import com.etiya.customerservice.repository.DistrictRepository;
import org.springframework.stereotype.Service;

@Service
public class DistrictBusinessRoles {
    private final DistrictRepository districtRepository;

    public DistrictBusinessRoles(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public void checkIfAddressExists(int id){
        District district = districtRepository.findById(id).orElseThrow(() -> new BusinessException("District with id " + id + " does not exist"));
        if (!district.getAddresses().isEmpty()){
            throw new BusinessException("This district has an address");
        }
    }

    public void checkIfCityExists(int id){

    }
}
