package com.etiya.customerservice.service.abstracts;


import com.etiya.customerservice.service.requests.address.CreateAddressRequest;
import com.etiya.customerservice.service.requests.address.UpdateAddressRequest;
import com.etiya.customerservice.service.requests.addressorchestratorrequest.CreateFullAddressRequest;
import com.etiya.customerservice.service.responses.address.CreatedAddressResponse;
import com.etiya.customerservice.service.responses.address.GetByIdAddressResponse;
import com.etiya.customerservice.service.responses.address.GetListAddressResponse;
import com.etiya.customerservice.service.responses.address.UpdatedAddressResponse;

import java.util.List;
import java.util.UUID;

public interface AddressService {

    // void add(Address address);
    CreatedAddressResponse add(CreateAddressRequest request); //domainn kendisi yok. doğrudan adress var

    //List<Address> getAll();
    List<GetListAddressResponse>  getList();

    void delete(int id);

    void softDelete(int id);

    UpdatedAddressResponse update(UpdateAddressRequest request);

    GetByIdAddressResponse getById(int id);




}
