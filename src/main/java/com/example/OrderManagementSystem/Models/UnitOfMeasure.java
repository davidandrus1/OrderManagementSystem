package com.example.OrderManagementSystem.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "units_of_measures")
public class UnitOfMeasure extends BaseModel {

    @NotBlank(message = "Unit of measure name is required")
    @Size(min = 1, max = 128, message = "Unit of measure name must be between 1 and 128 characters")
    private String name;

    @NotBlank(message = "Symbol is required")
    @Size(min = 1, max = 8, message = "Symbol must be between 1 and 8 characters")
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
