package com.example.searchservice.controller;

import com.example.searchservice.domain.CustomerSearch;
import com.example.searchservice.service.CustomerSearchService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/customer-search/")
public class CustomerSearchController {

    private final CustomerSearchService customerSearchService;

    public CustomerSearchController(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }

    @GetMapping("/get-customer-by-id")
    @ResponseStatus(HttpStatus.OK)
    public CustomerSearch getCustomerById(@RequestParam String id) {
        return customerSearchService.getById(id);
    }

    @GetMapping("/check-national-id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Boolean>> checkNationalId(@RequestParam String nationalId) {
        boolean exists = customerSearchService.existsByNationalId(nationalId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

    @GetMapping("search")
    public List<CustomerSearch> search(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String customerNumber,
            @RequestParam(required = false) String nationalId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String value,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return customerSearchService.searchDynamic(id, customerNumber, nationalId, firstName, lastName, value, page, size);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> findAll(){
        return customerSearchService.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id){
        customerSearchService.delete(id);
    }

    @GetMapping("fulltext")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> search(@RequestParam String keyword){
        return customerSearchService.searchAllFields(keyword);
    }

    @GetMapping("search-matched")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> searchByMatchedName(@RequestParam String name){
        return customerSearchService.searchByMatchedName(name);
    }

    @GetMapping("search-term")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> searchByExactValue(@RequestParam String nationalId){
        return customerSearchService.searchByExactValue(nationalId);
    }

    @GetMapping("search-fuzzy")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> searchBySimilarName(@RequestParam String name){
        return customerSearchService.searchBySimilarName(name);
    }

    @GetMapping("search-range")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> searchByDateRange(@RequestParam String start , @RequestParam String end){
        return customerSearchService.searchByDateRange(start,end);
    }

    @GetMapping("search-bool")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> searchByNameAndGender(String name, String gender){
        return customerSearchService.searchByNameAndGender(name,gender);
    }
}