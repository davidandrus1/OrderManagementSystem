package com.example.OrderManagementSystem.model;

public class Product extends SellableItem {

    private double value;

    public Product(){};

    public Product(String id, String name, double value) {
        super(id, name);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}