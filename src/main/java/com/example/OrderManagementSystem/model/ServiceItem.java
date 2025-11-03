package com.example.OrderManagementSystem.model;

public class ServiceItem extends SellableItem {

    private String status;

    public ServiceItem(String id, String name, String status) {
        super(id, name);
        setStatus(status);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if ("Active".equals(status) || "Down".equals(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Status must be 'Active' or 'Down'");
        }
    }
}