package com.etiya.customerservice.service.mappings;

import com.etiya.common.responses.GetAddressResponse;
import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.service.requests.address.CreateAddressRequest;
import com.etiya.customerservice.service.requests.address.UpdateAddressRequest;
import com.etiya.customerservice.service.responses.address.CreatedAddressResponse;
import com.etiya.customerservice.service.responses.address.GetByIdAddressResponse;
import com.etiya.customerservice.service.responses.address.GetListAddressResponse;
import com.etiya.customerservice.service.responses.address.UpdatedAddressResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(target = "districtName",source = "district.name") //manuel mapping burada yapılıyor
    @Mapping(target = "cityName",source = "district.city.name")
    @Mapping(target = "cityId",source = "district.city.id")
    @Mapping(target = "customerId",source = "customer.id")
    @Mapping(target = "districtId",source = "district.id")
    GetListAddressResponse getListAddressResponseFromAddress(Address address);

    //------------

    @Mapping(target = "customer.id",source = "customerId")
    @Mapping(target = "district.id",source = "districtId")
    @Mapping(target = "district.name",source = "districtName")
    @Mapping(target="district.city.id", source="cityId")
    @Mapping(target = "district.city.name",source = "cityName")
    Address addressFromCreateAddressRequest(CreateAddressRequest createAddressRequest);

    @Mapping(target = "customerId",source = "customer.id")
    @Mapping(target = "districtId",source = "district.id")
    @Mapping(target = "districtName",source = "district.name")
    @Mapping(target = "cityId",source = "district.city.id")
    @Mapping(target = "cityName",source = "district.city.name")
    CreatedAddressResponse createdAddressResponseFromAddress(Address address);

    //----------

    Address addressFromUpdateAddressRequest(UpdateAddressRequest updateAddressRequest, @MappingTarget Address address);

    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "customerId",source = "customer.id")
    @Mapping(target = "districtId",source = "district.id")
    @Mapping(target = "districtName",source = "district.name")
    @Mapping(target = "cityId",source = "district.city.id")
    @Mapping(target = "cityName",source = "district.city.name")
    UpdatedAddressResponse updatedAddressResponseFromAddress(Address address);


    List<GetListAddressResponse> getListAddressResponsesFromAddressList(List<Address> addressList);

    @Mapping(target = "districtName",source = "district.name") //manuel mapping burada yapılıyor
    @Mapping(target = "cityName",source = "district.city.name")
    @Mapping(target = "customerId",source = "customer.id")
    @Mapping(target = "customerFirstName",source = "customer.customerNumber")
    @Mapping(target = "districtId",source = "district.id")
    GetByIdAddressResponse getAddressResponseFromAddress(Address address);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "street", source = "street")
    @Mapping(target = "houseNumber", source = "houseNumber")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "districtId", source = "district.id")
    @Mapping(target = "districtName", source = "district.name")
    @Mapping(target = "cityId", source = "district.city.id")
    @Mapping(target = "cityName", source = "district.city.name")
    GetAddressResponse addressToGetAddressResponse(Address address);


}
