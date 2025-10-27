package com.example.OrderManagementSystem.model;

public abstract class SellableItem {

    public String id;

    public String name;

    public SellableItem() {}

    // Constructor cu parametri
    public SellableItem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter și Setter
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
}
