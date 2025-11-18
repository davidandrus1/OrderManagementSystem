package com.example.OrderManagementSystem.model;
import java.util.List;

public class Customer extends BaseModel {

    public String name;

    public String currency;

    public List<Order> orders;

    public List<Contract> contracts;

    public Customer(){};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contract> getActiveContracts() {
        return contracts.stream()
                .filter(c -> "ACTIVE".equalsIgnoreCase(c.getStatus()))
                .toList();
    }
}
