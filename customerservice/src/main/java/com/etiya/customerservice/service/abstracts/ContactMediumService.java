package com.etiya.customerservice.service.abstracts;

import com.etiya.customerservice.service.requests.contactmedium.CreateContactMediumRequest;
import com.etiya.customerservice.service.requests.contactmedium.UpdateContactMediumRequest;
import com.etiya.customerservice.service.responses.contactmedium.CreatedContactMediumResponse;
import com.etiya.customerservice.service.responses.contactmedium.GetByIdContactMediumResponse;
import com.etiya.customerservice.service.responses.contactmedium.GetListContactMediumResponse;
import com.etiya.customerservice.service.responses.contactmedium.UpdatedContactMediumResponse;

import java.util.List;

public interface ContactMediumService {
    CreatedContactMediumResponse add(CreateContactMediumRequest request);
    UpdatedContactMediumResponse update(UpdateContactMediumRequest request);
    List<GetListContactMediumResponse> getList();

    void delete(int id);

    void softDelete(int id);

    GetByIdContactMediumResponse getById(int id);
    GetByIdContactMediumResponse getByValue(String value);
    List<GetListContactMediumResponse> getListByType(String type);
    List<GetListContactMediumResponse> getListByCustomerId(int customerId);
}
