package com.etiya.customerservice.service.mappings;

import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.domain.entities.District;
import com.etiya.customerservice.service.requests.district.CreateDistrictRequest;
import com.etiya.customerservice.service.requests.district.UpdateDistrictRequest;
import com.etiya.customerservice.service.responses.district.CreatedDistrictResponse;
import com.etiya.customerservice.service.responses.district.GetByIdDistrictResponse;
import com.etiya.customerservice.service.responses.district.GetListDistrictResponse;
import com.etiya.customerservice.service.responses.district.UpdatedDistrictResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DistrictMapper {

    DistrictMapper INSTANCE = Mappers.getMapper(DistrictMapper.class);

    @Mapping(target = "cityId", source = "city.id")
    CreatedDistrictResponse createdDistrictResponseFromDistrict (District district);

    @Mapping(source = "cityId", target = "city.id")
    District districtFromCreateDistrictRequest (CreateDistrictRequest request);

    @Mapping(target = "cityId", source = "city.id")
    @Mapping(target = "addresses", source = "addresses")
    GetListDistrictResponse getListDistrictResponseFromDistrict(District district);

    List<GetListDistrictResponse> getListDistrictResponseFromDistrict(List<District> districts);

    @Mapping(target = "cityId", source = "city.id")
    UpdatedDistrictResponse updatedDistrictResponseFromDistrict (District district);

    @Mapping(target = "city.id", source = "cityId")
    District districtFromUpdateDistrictRequest(UpdateDistrictRequest request);

    District districtFromUpdateDistrictRequest(UpdateDistrictRequest request, @MappingTarget District district);

    @Mapping(target = "cityId", source = "city.id")
    @Mapping(target = "addresses", source = "addresses")
    GetByIdDistrictResponse getDistrictResponseFromDistrict(District district);


//    default City mapCity(int cityId) {
//        City city = new City();
//        city.setId(cityId);
//        return city;
//    } Bunun yerine @MappingTarget'siz de bir mapper fonksiyonu oluşturuyoruz.

    default Integer map(Address address) {
        return address.getId();
    }
}
