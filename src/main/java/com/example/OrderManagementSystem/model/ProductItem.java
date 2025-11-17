package com.example.OrderManagementSystem.model;

public class ProductItem extends SellableItem {

    private double value;

    public ProductItem(){};

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}