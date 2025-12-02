package com.example.OrderManagementSystem.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "sellable_items")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class SellableItem extends BaseModel {

    private String name;

    public SellableItem(String prefix) {
        this.setPrefix(prefix);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}