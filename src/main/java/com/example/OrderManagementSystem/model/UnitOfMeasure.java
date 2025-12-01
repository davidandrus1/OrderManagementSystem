package com.example.OrderManagementSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "units_of_measures")
public class UnitOfMeasure extends BaseModel {

    private String name;
    private String symbol;

    public UnitOfMeasure() {
        this.setPrefix("UM");
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
