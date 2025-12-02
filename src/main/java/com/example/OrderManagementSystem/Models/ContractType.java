package com.example.OrderManagementSystem.Models;

import jakarta.persistence.*;

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
