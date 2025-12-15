package com.example.OrderManagementSystem.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "service_items")
@PrimaryKeyJoinColumn(name = "sellable_item_id")
public class ServiceItem extends SellableItem {

    @NotBlank(message = "Status is required")
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