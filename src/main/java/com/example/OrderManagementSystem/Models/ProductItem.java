package com.example.OrderManagementSystem.Models;
import jakarta.persistence.*;

@Entity
@Table(name = "product_items")
@PrimaryKeyJoinColumn(name = "sellable_item_id")
public class ProductItem extends SellableItem {

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