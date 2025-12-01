package com.example.OrderManagementSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "contract_types")
public class ContractType extends BaseModel {

    private String name;
    private String type;

    public ContractType() {
        this.setPrefix("CT");
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

}
