package com.etiya.catalogservice.service.dtos.responses.catalog;

import java.util.List;

public class CreatedCatalogResponse {
    private int id;
    private String name;
    private int parentId;
    private List<Integer> subCatalogIds;

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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<Integer> getSubCatalogIds() {
        return subCatalogIds;
    }

    public void setSubCatalogIds(List<Integer> subCatalogIds) {
        this.subCatalogIds = subCatalogIds;
    }
}