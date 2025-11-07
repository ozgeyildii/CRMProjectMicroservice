package com.etiya.customerservice.controller;

import com.etiya.common.responses.CustomerResponse;
import com.etiya.customerservice.service.abstracts.IndividualCustomerService;
import com.etiya.customerservice.service.requests.individualcustomers.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.requests.individualcustomers.UpdateIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.customer.FullCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomers.CreatedIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualcustomers.UpdatedIndividualCustomerResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
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

    @GetMapping("/check-national-id")
    public ResponseEntity<Map<String, Object>> checkNationalId(@RequestParam String nationalId) {
        boolean exists= individualCustomerService.existsByNationalId(nationalId);
        Map<String, Object> response = new HashMap<>();
        response.put("exists", exists);
        if(exists){
            response.put("message", "A customer already exists with this Nationality ID. Please review and ensure all the fields are filled correctly.");
        }
        else
        {
            response.put("message", "National ID is available.");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/full-customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FullCustomerResponse getFullCustomerById(@PathVariable UUID id){
        return individualCustomerService.getFullCustomerById(id);
    }


}
