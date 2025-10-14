package com.etiya.customerservice.service.responses.city;

public class GetListCityResponse {
    private int id;
    private String name;

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

    public GetListCityResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public GetListCityResponse() {
    }
}
