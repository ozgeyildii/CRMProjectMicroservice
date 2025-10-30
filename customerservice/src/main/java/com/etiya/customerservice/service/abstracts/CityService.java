package com.etiya.customerservice.service.abstracts;

import com.etiya.customerservice.domain.entities.City;
import com.etiya.customerservice.service.requests.city.CreateCityRequest;
import com.etiya.customerservice.service.requests.city.UpdateCityRequest;
import com.etiya.customerservice.service.responses.city.CreatedCityResponse;
import com.etiya.customerservice.service.responses.city.GetByIdCityResponse;
import com.etiya.customerservice.service.responses.city.GetListCityResponse;
import com.etiya.customerservice.service.responses.city.UpdatedCityResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface CityService {
    //void add(City city);
    CreatedCityResponse add(CreateCityRequest request);
    UpdatedCityResponse update(UpdateCityRequest request);
    List<GetListCityResponse> getList();
    GetByIdCityResponse getById(int id);
    List<GetListCityResponse> findByCreatedDateBiggerThanParameter(LocalDateTime parameter);
    List<GetListCityResponse> findByCreatedDateBiggerThanParameterNative(LocalDateTime parameter);
    List<GetListCityResponse> findByCreatedDate(LocalDateTime createdDate);
    City existsById(int id);
    City findOrCreateByName(String name);



}
