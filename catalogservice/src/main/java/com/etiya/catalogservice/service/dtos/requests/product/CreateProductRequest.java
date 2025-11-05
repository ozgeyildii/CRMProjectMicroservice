package com.etiya.catalogservice.service.dtos.requests.product;


public class CreateProductRequest {


    private String name;
    private int stock;
    private double price;
    private int catalogId;
    private int specId;

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }


    public int getSpecId() {
        return specId;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CreateProductRequest() {
    }

    public CreateProductRequest(String name, int stock, double price) {
        this.name = name;
        this.stock = stock;
        this.price = price;
    }
}