package com.etiya.customerservice.service.mappings;

import com.etiya.customerservice.domain.entities.ContactMedium;
import com.etiya.customerservice.service.requests.contactmedium.CreateContactMediumRequest;
import com.etiya.customerservice.service.requests.contactmedium.UpdateContactMediumRequest;
import com.etiya.customerservice.service.responses.contactmedium.CreatedContactMediumResponse;
import com.etiya.customerservice.service.responses.contactmedium.GetByIdContactMediumResponse;
import com.etiya.customerservice.service.responses.contactmedium.GetListContactMediumResponse;
import com.etiya.customerservice.service.responses.contactmedium.UpdatedContactMediumResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContactMediumMapper {

    ContactMediumMapper INSTANCE = Mappers.getMapper(ContactMediumMapper.class);

    @Mapping(source = "customerId",target = "customer.id")
    ContactMedium getContactMediumFromCreateContactMediumRequest(CreateContactMediumRequest createContactMediumRequest);

    CreatedContactMediumResponse getCreatedContactMediumResponseFromContactMedium(ContactMedium contactMedium);

    @Mapping(source = "customer.id",target = "customerId")
    @Mapping(source = "type", target = "type")
    GetListContactMediumResponse toGetListResponse(ContactMedium contactMedium);

    @Mapping(source = "customer.id",target = "customerId")
    List<GetListContactMediumResponse> getListContactMediumResponsesFromContactMedium(List<ContactMedium> contactMediums);



    @Mapping(target = "customer.id",source = "customerId")
    ContactMedium contactMediumFromUpdateContactMediumRequest(UpdateContactMediumRequest updateContactMediumRequest);

    ContactMedium contactMediumFromUpdateContactMediumRequest(UpdateContactMediumRequest updateContactMediumRequest, @MappingTarget ContactMedium contactMedium);

    @Mapping(target = "customerId",source = "customer.id")
    UpdatedContactMediumResponse getUpdatedContactMediumResponseFromContactMedium(ContactMedium contactMedium);

    GetByIdContactMediumResponse getContactMediumResponseFromContactMedium(ContactMedium contactMedium);
}
