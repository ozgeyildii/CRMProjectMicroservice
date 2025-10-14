package com.etiya.customerservice.service.mappings;

import com.etiya.customerservice.domain.entities.City;
import com.etiya.customerservice.service.requests.city.CreateCityRequest;
import com.etiya.customerservice.service.requests.city.UpdateCityRequest;
import com.etiya.customerservice.service.responses.city.CreatedCityResponse;
import com.etiya.customerservice.service.responses.city.GetByIdCityResponse;
import com.etiya.customerservice.service.responses.city.GetListCityResponse;
import com.etiya.customerservice.service.responses.city.UpdatedCityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    City cityFromCreateCityRequest(CreateCityRequest createCityRequest);
    CreatedCityResponse createdCityResponseFromCity(City city);

    City cityFromUpdateCityRequest(UpdateCityRequest updateCityRequest, @MappingTarget City city);
    UpdatedCityResponse updatedCityResponseFromCity(City city);

    List<GetListCityResponse> getListCityResponseFromCity(List<City> cities);

    GetByIdCityResponse getByIdCityResponseFromCity(City city);


}