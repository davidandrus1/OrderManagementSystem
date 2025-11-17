package com.example.OrderManagementSystem.model;

public abstract class BaseModel {

    public String id;

    public BaseModel() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}