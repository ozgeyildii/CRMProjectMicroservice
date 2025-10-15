package com.etiya.customerservice.controller;

import com.etiya.customerservice.service.abstracts.ContactMediumService;
import com.etiya.customerservice.service.requests.contactmedium.CreateContactMediumRequest;
import com.etiya.customerservice.service.requests.contactmedium.UpdateContactMediumRequest;
import com.etiya.customerservice.service.responses.contactmedium.CreatedContactMediumResponse;
import com.etiya.customerservice.service.responses.contactmedium.GetByIdContactMediumResponse;
import com.etiya.customerservice.service.responses.contactmedium.GetListContactMediumResponse;
import com.etiya.customerservice.service.responses.contactmedium.UpdatedContactMediumResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contactmediums")
public class ContactMediumController {
    private final ContactMediumService contactMediumService;

    public ContactMediumController(ContactMediumService contactMediumService) {
        this.contactMediumService = contactMediumService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedContactMediumResponse createContactMedium(@Valid @RequestBody CreateContactMediumRequest request) {
        return contactMediumService.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedContactMediumResponse updateContactMedium(@RequestBody UpdateContactMediumRequest request) {
        return contactMediumService.update(request);
    }

    @DeleteMapping("{id}")//pathvariable ile anlaşsın diye, mapping yapsın diye
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        contactMediumService.delete(id);
    }

    @DeleteMapping("{id}/soft")
    @ResponseStatus(HttpStatus.OK)
    public void softDelete(@PathVariable int id){
        contactMediumService.softDelete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListContactMediumResponse> getList(){
        return contactMediumService.getList();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetByIdContactMediumResponse getById(@PathVariable int id) {
        return contactMediumService.getById(id);
    }

    @GetMapping("{value}/value") // <-- BU KURAL KESİNLİKLE KALMALI
    @ResponseStatus(HttpStatus.OK)
    public GetByIdContactMediumResponse getByValueStartsWith(@PathVariable String value) {
        return contactMediumService.getByValue(value);
    }

    @GetMapping("{type}/type") // <-- BU KURAL KESİNLİKLE KALMALI
    @ResponseStatus(HttpStatus.OK)
    public List<GetListContactMediumResponse> getListByType(@PathVariable String type) {
        return contactMediumService.getListByType(type);
    }
    @GetMapping("{customerId}/customerId") // <-- BU KURAL KESİNLİKLE KALMALI
    @ResponseStatus(HttpStatus.OK)
    public List<GetListContactMediumResponse> getListByCustomerId(@PathVariable int id) {
        return contactMediumService.getListByCustomerId(id);
    }



}
