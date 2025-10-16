package com.example.searchservice.controller;

import com.example.searchservice.domain.CustomerSearch;
import com.example.searchservice.service.CustomerSearchService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/customer-search/")
public class CustomerSearchController {

    private final CustomerSearchService customerSearchService;

    public CustomerSearchController(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
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
    public List<CustomerSearch> searchByDateRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start , @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end){
        return customerSearchService.searchByDateRange(start,end);
    }

    @GetMapping("search-bool")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> searchByNameAndGender(String name, String gender){
        return customerSearchService.searchByNameAndGender(name,gender);
    }
}