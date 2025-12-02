package com.example.OrderManagementSystem.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "service_items")
@PrimaryKeyJoinColumn(name = "sellable_item_id")
public class ServiceItem extends SellableItem {

    private String status;

    public ServiceItem() {
        super("SERV");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}