package com.example.OrderManagementSystem.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "orders_lines")
public class OrderLine extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "sellable_item_id", nullable = false)
    private SellableItem sellableItem;

    @ManyToOne
    @JoinColumn(name = "unit_of_measure_id", nullable = false)
    private UnitOfMeasure unitOfMeasure;

    private Double quantity;

    public OrderLine() {
        this.setPrefix("OL");  // Order Line prefix
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public SellableItem getSellableItem() {
        return sellableItem;
    }

    public void setSellableItem(SellableItem sellableItem) {
        this.sellableItem = sellableItem;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}