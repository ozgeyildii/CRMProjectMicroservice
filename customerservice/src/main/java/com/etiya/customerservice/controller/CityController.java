package com.etiya.customerservice.controller;

import com.etiya.customerservice.service.abstracts.CityService;
import com.etiya.customerservice.service.requests.city.CreateCityRequest;
import com.etiya.customerservice.service.requests.city.UpdateCityRequest;
import com.etiya.customerservice.service.responses.city.CreatedCityResponse;
import com.etiya.customerservice.service.responses.city.GetByIdCityResponse;
import com.etiya.customerservice.service.responses.city.GetListCityResponse;
import com.etiya.customerservice.service.responses.city.UpdatedCityResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

/*@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public void add(@RequestBody City city){
    cityService.add(city);
}*/

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCityResponse add(@Valid @RequestBody CreateCityRequest createCityRequest) {
        return cityService.add(createCityRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCityResponse update(@Valid @RequestBody UpdateCityRequest updateCityRequest) {
        return cityService.update(updateCityRequest);
    }

    @GetMapping("/getListCityResponse")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListCityResponse> getList(){
        return cityService.getList();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetByIdCityResponse getById(@PathVariable int id){
        return cityService.getById(id);
    }

    @GetMapping("findByCreatedDateBiggerThanParameter/{parameter}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListCityResponse> findByCreatedDateBiggerThanParameter(@PathVariable LocalDateTime parameter){
        return cityService.findByCreatedDateBiggerThanParameter(parameter);
    }

    @GetMapping("findByCreatedDateBiggerThanParameterNative/{parameter}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListCityResponse> findByCreatedDateBiggerThanParameterNative(@PathVariable LocalDateTime parameter){
        return cityService.findByCreatedDateBiggerThanParameter(parameter);
    }

    @GetMapping("findByCreatedDate/{createdDate}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListCityResponse> findByCreatedDate(@PathVariable  LocalDateTime createdDate){
        return cityService.findByCreatedDate(createdDate);
    }



}