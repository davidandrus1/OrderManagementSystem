package com.example.OrderManagementSystem.model;

import jakarta.persistence.*;

@MappedSuperclass
public class BaseModel {

    @Id
    @Column(length = 20, nullable = false, updatable = false)
    private String id;

    @Transient  // Nu se salvează în DB
    private String prefix;

    public BaseModel() {}

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

}
