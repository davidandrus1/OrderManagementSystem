package com.example.OrderManagementSystem.model;

public class ContractLine {

    public String id;

    SellableItem item;

    UnitOfMeasure unit;

    public double quantity;

    public ContractLine() {}


    public ContractLine(String id, SellableItem item, UnitOfMeasure unit, double quantity) {
        this.id = id;
        this.item = item;
        this.unit = unit;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
