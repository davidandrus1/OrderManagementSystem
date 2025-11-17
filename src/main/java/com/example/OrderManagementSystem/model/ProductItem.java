package com.example.OrderManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

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