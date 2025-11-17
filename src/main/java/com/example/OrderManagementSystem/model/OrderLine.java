package com.example.OrderManagementSystem.model;

public class OrderLine extends BaseModel {

    SellableItem item;

    UnitOfMeasure unit;

    public double quantity;

    public SellableItem getItem() {
        return item;
    }

    public void setItem(SellableItem item) {
        this.item = item;
    }

    public UnitOfMeasure getUnit() {
        return unit;
    }

    public void setUnit(UnitOfMeasure unit) {
        this.unit = unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
