package com.example.OrderManagementSystem.model;


import java.util.List;

public class Customer {
    public String id;
    public String name;
    public String currency;
    public List<Order> orders;
    public List<Contract> contracts;
    public Customer(String id, String name, String currency, List<Order> orders, List<Contract> contracts) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.orders = orders;
        this.contracts = contracts;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }
}
