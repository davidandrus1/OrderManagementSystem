package com.example.OrderManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceItem extends SellableItem{

    public enum Status { ACTIVE, DOWN }

    private Status status;

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }

}