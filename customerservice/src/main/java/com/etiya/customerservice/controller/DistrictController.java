package com.etiya.customerservice.controller;

import com.etiya.customerservice.service.abstracts.DistrictService;
import com.etiya.customerservice.service.requests.district.CreateDistrictRequest;
import com.etiya.customerservice.service.requests.district.UpdateDistrictRequest;
import com.etiya.customerservice.service.responses.district.CreatedDistrictResponse;
import com.etiya.customerservice.service.responses.district.GetByIdDistrictResponse;
import com.etiya.customerservice.service.responses.district.GetListDistrictResponse;
import com.etiya.customerservice.service.responses.district.UpdatedDistrictResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {

    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedDistrictResponse add(@Valid @RequestBody CreateDistrictRequest request) {
        return districtService.add(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListDistrictResponse> getAll() {
        return districtService.getAll();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedDistrictResponse update(@Valid @RequestBody UpdateDistrictRequest request) {
        return districtService.update(request);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetByIdDistrictResponse getById(@PathVariable int id) {
        return districtService.getById(id);
    }

    @GetMapping("getDistrictsByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListDistrictResponse> getDistrictsByName(@PathVariable String name) {
        return districtService.getByName(name);
    }

    @GetMapping("getByNameStartingWith/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListDistrictResponse> getByNameStartingWith(@PathVariable String name) {
        return districtService.getByNameStartingWith(name);
    }

    @GetMapping("getByCityId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListDistrictResponse> getByCityId(@PathVariable int id) {
        return districtService.getByCityId(id);
    }

    @DeleteMapping("{id}")//pathvariable ile anlaşsın diye, mapping yapsın diye
    @ResponseStatus(HttpStatus.OK)
    public void delete(@Valid @PathVariable int id) {
        districtService.deleteById(id);
    }
}
