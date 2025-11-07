package com.etiya.customerservice.controller;

import com.etiya.customerservice.service.abstracts.AddressService;
import com.etiya.customerservice.service.requests.address.CreateAddressRequest;
import com.etiya.customerservice.service.requests.address.UpdateAddressRequest;
import com.etiya.customerservice.service.responses.address.CreatedAddressResponse;
import com.etiya.customerservice.service.responses.address.GetByIdAddressResponse;
import com.etiya.customerservice.service.responses.address.GetListAddressResponse;
import com.etiya.customerservice.service.responses.address.UpdatedAddressResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /*@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody Address address) {
        addressService.add(address);
    }
*/
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAddressResponse add(@Valid @RequestBody CreateAddressRequest request)
    {
        return addressService.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedAddressResponse update(@RequestBody UpdateAddressRequest request){
        return addressService.update(request);
    }

    @GetMapping("get-all/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListAddressResponse> getList(@PathVariable UUID customerId){
        return addressService.getListByCustomerId(customerId);
    }
/*
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Address> getAllAddresses() {
        return addressService.getAll();
    }*/

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListAddressResponse> getAllAddresses() {
        return addressService.getList();
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@Valid @PathVariable int id) {
        addressService.delete(id);
    }

    @DeleteMapping("{id}/soft")
    @ResponseStatus(HttpStatus.OK)
    public void softDelete(@PathVariable int id){
        addressService.softDelete(id);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetByIdAddressResponse getById(@PathVariable int id) {
        return addressService.getById(id);
    }

    @PatchMapping("/{id}/set-primary")
    @ResponseStatus(HttpStatus.OK)
    public void setPrimary(@PathVariable int id) {
        addressService.setPrimaryAddress(id);
    }
}
