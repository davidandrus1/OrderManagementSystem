package com.example.OrderManagementSystem.model;

public class UnitOfMeasure extends BaseModel {

    public String name;

    public String symbol;

    public UnitOfMeasure(){};

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol =symbol;
    }
}