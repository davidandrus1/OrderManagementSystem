package com.example.OrderManagementSystem.model;

public class ServiceItem extends SellableItem{

    public enum Status { ACTIVE, DOWN }

    private Status status;

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }

}