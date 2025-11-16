package com.example.OrderManagementSystem.model;

public class ContractType extends BaseModel{

    public String name;

    public String type; // "customer" sau "seller"

    public ContractType() {};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
