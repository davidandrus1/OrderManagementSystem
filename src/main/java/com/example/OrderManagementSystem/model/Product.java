package com.example.OrderManagementSystem.model;

public class Product {

    public String id;

    public String name;

    public double Value;

    public Product(String id, String name, double value) {
        this.id = id;
        this.name = name;
        this.Value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        this.Value = value;
    }
}

