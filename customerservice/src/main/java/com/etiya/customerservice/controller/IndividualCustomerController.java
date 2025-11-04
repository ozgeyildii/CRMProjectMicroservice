package com.etiya.customerservice.controller;

import com.etiya.common.responses.CustomerResponse;
import com.etiya.customerservice.service.abstracts.IndividualCustomerService;
import com.etiya.customerservice.service.requests.individualcustomers.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.requests.individualcustomers.UpdateIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.individualcustomers.CreatedIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomers.UpdatedIndividualCustomerResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/individual-customers")
public class IndividualCustomerController {
    private final IndividualCustomerService individualCustomerService;

    public IndividualCustomerController(IndividualCustomerService individualCustomerService) {
        this.individualCustomerService = individualCustomerService;
    }

    @PostMapping  // Artık gateway üzerinden istek gidecek //localhost:8091/customerservice/api/individual-customers
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedIndividualCustomerResponse add(@Valid @RequestBody CreateIndividualCustomerRequest request){
        return individualCustomerService.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedIndividualCustomerResponse update(@RequestBody UpdateIndividualCustomerRequest request){
        return individualCustomerService.update(request);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        individualCustomerService.delete(id);
    }


    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getById(@PathVariable UUID id){
        return individualCustomerService.getById(id);
    }

}
