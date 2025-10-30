package com.etiya.customerservice.service.concretes;


import com.etiya.customerservice.domain.entities.City;
import com.etiya.customerservice.repository.CityRepository;
import com.etiya.customerservice.service.abstracts.CityService;
import com.etiya.customerservice.service.mappings.CityMapper;
import com.etiya.customerservice.service.requests.city.CreateCityRequest;
import com.etiya.customerservice.service.requests.city.UpdateCityRequest;
import com.etiya.customerservice.service.responses.city.CreatedCityResponse;
import com.etiya.customerservice.service.responses.city.GetByIdCityResponse;
import com.etiya.customerservice.service.responses.city.GetListCityResponse;
import com.etiya.customerservice.service.responses.city.UpdatedCityResponse;
import com.etiya.customerservice.service.rules.CityBusinessRules;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityBusinessRules cityBusinessRules;

    public CityServiceImpl(CityRepository cityRepository, CityBusinessRules cityBusinessRules) {
        this.cityRepository = cityRepository;
        this.cityBusinessRules = cityBusinessRules;
    }


    @Override
    public CreatedCityResponse add(CreateCityRequest request) {
        cityBusinessRules.checkCityNameAlreadyExists(request.getName());
        City city = CityMapper.INSTANCE.cityFromCreateCityRequest(request);
        City createdCity = cityRepository.save(city);
        CreatedCityResponse response=CityMapper.INSTANCE.createdCityResponseFromCity(createdCity);
        return response;
    }

    @Override
    public UpdatedCityResponse update(UpdateCityRequest request) {
        cityBusinessRules.checkCityNameAlreadyExists(request.getName());
        City city =cityRepository.findById(request.getId()).orElseThrow(()->new RuntimeException("City not found"));
        City mappedCity = CityMapper.INSTANCE.cityFromUpdateCityRequest(request, city);
        City updatedCity=cityRepository.save(mappedCity);
        UpdatedCityResponse response=CityMapper.INSTANCE.updatedCityResponseFromCity(updatedCity);
        return response;
    }

    @Override
    public List<GetListCityResponse> getList() {
        List<City> foundCities = cityRepository.findAll();
        List<GetListCityResponse> responses = CityMapper.INSTANCE.getListCityResponseFromCity(foundCities);
        return responses;
    }

    @Override
    public GetByIdCityResponse getById(int id) {
        City city = cityRepository.findById(id).orElseThrow(()->new RuntimeException("City not found"));
        GetByIdCityResponse response=CityMapper.INSTANCE.getByIdCityResponseFromCity(city);
        return response;
    }

    @Override
    public List<GetListCityResponse> findByCreatedDateBiggerThanParameter(LocalDateTime parameter) {
        List<City> cities=cityRepository.findByCreatedDateBiggerThanParameter(parameter);
        List<GetListCityResponse> responses=CityMapper.INSTANCE.getListCityResponseFromCity(cities);
        return  responses;
    }

    @Override
    public List<GetListCityResponse> findByCreatedDateBiggerThanParameterNative(LocalDateTime parameter) {
        List<City> cities=cityRepository.findByCreatedDateBiggerThanParameterNative(parameter);
        List<GetListCityResponse> responses=CityMapper.INSTANCE.getListCityResponseFromCity(cities);
        return responses;
    }

    @Override
    public List<GetListCityResponse> findByCreatedDate(LocalDateTime  createdDate) {
        List<City> cities=cityRepository.findByCreatedDate(createdDate);
        List<GetListCityResponse> responses=CityMapper.INSTANCE.getListCityResponseFromCity(cities);
        return responses;
    }

    @Override
    public City existsById(int id) {
        cityBusinessRules.checkIfCityExistsById(id);
        return cityRepository.findById(id).get();
    }

    @Override
    public City findOrCreateByName(String name) {
        return cityRepository.findByNameIgnoreCase(name)
                .orElseGet(() -> cityRepository.save(new City(0, name, null)));
    }
/*
    @Override
    public void add(City city) {
        cityRepository.save(city);
    }*/


}