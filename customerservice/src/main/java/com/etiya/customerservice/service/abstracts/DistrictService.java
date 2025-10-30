package com.etiya.customerservice.service.abstracts;

import com.etiya.customerservice.domain.entities.City;
import com.etiya.customerservice.domain.entities.District;
import com.etiya.customerservice.service.requests.district.CreateDistrictRequest;
import com.etiya.customerservice.service.requests.district.UpdateDistrictRequest;
import com.etiya.customerservice.service.responses.district.CreatedDistrictResponse;
import com.etiya.customerservice.service.responses.district.GetByIdDistrictResponse;
import com.etiya.customerservice.service.responses.district.GetListDistrictResponse;
import com.etiya.customerservice.service.responses.district.UpdatedDistrictResponse;

import java.util.List;

public interface DistrictService {
    CreatedDistrictResponse add(CreateDistrictRequest request);
    List<GetListDistrictResponse> getAll();
    UpdatedDistrictResponse update(UpdateDistrictRequest request);
    GetByIdDistrictResponse getById(int id);
    void deleteById(int id);
    List<GetListDistrictResponse> getByName(String name);
    List<GetListDistrictResponse> getByNameStartingWith(String name);
    List<GetListDistrictResponse> getByCityId(int cityId);
    District findOrCreateByNameAndCity(String name, City city);
    District findByName(String name);


}
