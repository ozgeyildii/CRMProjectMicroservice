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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses/")
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

    // post kullanılabilir ama Best practice için o sınıfınmappingi yazılır
    @DeleteMapping("{id}")//pathvariable ile anlaşsın diye, mapping yapsın diye
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
}
