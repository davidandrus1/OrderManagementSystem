package com.example.OrderManagementSystem.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "product_items")
@PrimaryKeyJoinColumn(name = "sellable_item_id")
public class ProductItem extends SellableItem {

    @NotNull(message = "Value is required")
    @Positive(message = "Value must be greater than zero")
    private Double value;

    public ProductItem() {
        super("PROD");
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}