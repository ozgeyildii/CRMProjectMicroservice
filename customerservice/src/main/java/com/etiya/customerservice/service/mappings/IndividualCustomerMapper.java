package com.etiya.customerservice.service.mappings;

import com.etiya.customerservice.domain.entities.City;
import com.etiya.customerservice.domain.entities.IndividualCustomer;
import com.etiya.customerservice.service.requests.city.UpdateCityRequest;
import com.etiya.customerservice.service.requests.individualcustomers.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.requests.individualcustomers.UpdateIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.city.UpdatedCityResponse;
import com.etiya.customerservice.service.responses.individualcustomers.CreatedIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomers.UpdatedIndividualCustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IndividualCustomerMapper {

    IndividualCustomerMapper INSTANCE = Mappers.getMapper(IndividualCustomerMapper.class);

    IndividualCustomer individualCustomerFromCreateIndividualCustomerRequest(CreateIndividualCustomerRequest createIndividualCustomerRequest);

    CreatedIndividualCustomerResponse createdIndividualCustomerResponseFromIndividualCustomer(IndividualCustomer individualCustomer);

    IndividualCustomer individualCustomerFromUpdateIndividualCustomerRequest(UpdateIndividualCustomerRequest updateIndividualCustomerRequest, @MappingTarget IndividualCustomer individualCustomer);
    UpdatedIndividualCustomerResponse updatedIndividualCustomerResponseFromIndividualCustomer(IndividualCustomer individualCustomer);
}
