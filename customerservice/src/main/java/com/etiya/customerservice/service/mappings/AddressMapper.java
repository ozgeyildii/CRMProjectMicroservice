package com.etiya.customerservice.service.mappings;

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
    @Mapping(target = "customerId",source = "customer.id")
    @Mapping(target = "districtId",source = "district.id")
    GetListAddressResponse getListAddressResponseFromAddress(Address address);

    //------------

    @Mapping(source = "customerId",target = "customer.id")
    @Mapping(source = "districtId",target = "district.id")
    Address addressFromCreateAddressRequest(CreateAddressRequest createAddressRequest);

    @Mapping(target = "customerId",source = "customer.id")
    @Mapping(target = "districtId",source = "district.id")
    CreatedAddressResponse createdAddressResponseFromAddress(Address address);

    //----------

    @Mapping(source = "districtId",target = "district.id")
    Address addressFromUpdateAddressRequest(UpdateAddressRequest updateAddressRequest, @MappingTarget Address address);

    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "customerId",source = "customer.id")
    @Mapping(target = "districtId",source = "district.id")
    UpdatedAddressResponse updatedAddressResponseFromAddress(Address address);

    List<GetListAddressResponse> getListAddressResponsesFromAddressList(List<Address> addressList);

    @Mapping(target = "districtName",source = "district.name") //manuel mapping burada yapılıyor
    @Mapping(target = "cityName",source = "district.city.name")
    @Mapping(target = "customerId",source = "customer.id")
    @Mapping(target = "customerFirstName",source = "customer.customerNumber")
    @Mapping(target = "districtId",source = "district.id")
    GetByIdAddressResponse getAddressResponseFromAddress(Address address);
}
