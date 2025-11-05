package com.etiya.catalogservice.service.dtos.responses.product;

public class CreatedProductResponse {

    private int id;
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

    public CreatedProductResponse() {
    }

    public CreatedProductResponse(int id, String name, int stock, double price) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
    }
}