package com.example.OrderManagementSystem.model;

public class ContractType {
    public String id;

    public String name;

    public String type; // "customer" sau "seller"

    public ContractType(String name, String type) {
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
