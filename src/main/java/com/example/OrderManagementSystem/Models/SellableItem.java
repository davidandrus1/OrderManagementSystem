package com.example.OrderManagementSystem.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "sellable_items")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class SellableItem extends BaseModel {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 128, message = "Name must be between 2 and 128 characters")
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