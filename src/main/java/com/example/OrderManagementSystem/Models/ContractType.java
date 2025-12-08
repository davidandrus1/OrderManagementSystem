package com.example.OrderManagementSystem.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "contract_types")
public class ContractType extends BaseModel {

    @NotBlank(message = "Contract type name is required")
    @Size(min = 2, max = 128, message = "Contract type name must be between 2 and 128 characters")
    private String name;

    @NotBlank(message = "Type is required")
    @Size(min = 3, max = 16, message = "Type must be between 3 and 16 characters")
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
