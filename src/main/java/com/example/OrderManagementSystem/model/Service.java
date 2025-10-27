package com.example.OrderManagementSystem.model;

public class Service {

    public String id;

    public String name;

    public String Status;

    public Service(String id, String name, String Status){
        this.id = id;
        this.name = name;
        this.Status = Status;
    }

    public String getId(){return id;}

    public String setId(String id){
        return this.id = id;
    }

    public String getName(){return name;}

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        if (status.equals("Active") || status.equals("Down")) {
            this.Status = status;
        } else {
            throw new IllegalArgumentException("Status needs to be 'Active' or 'Down'.");
        }
    }
}
