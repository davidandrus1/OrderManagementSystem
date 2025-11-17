package com.example.OrderManagementSystem.model;

public abstract class SellableItem extends BaseModel{

    public String name;

    public SellableItem() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
