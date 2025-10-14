package com.etiya.customerservice.service.responses.district;

import java.util.List;

public class GetByIdDistrictResponse {
    private int id;
    private String name;
    private int cityId;
    private List<Integer> addresses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public List<Integer> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Integer> addresses) {
        this.addresses = addresses;
    }

    public GetByIdDistrictResponse(int id, String name, int cityId, List<Integer> addresses) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
        this.addresses = addresses;
    }

    public GetByIdDistrictResponse() {
    }
}
