package com.example.OrderManagementSystem.Models;

import com.example.OrderManagementSystem.Repositories.ContractTypeRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customers")
public class Customer extends BaseModel {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 128, message = "Name must be between 2 and 128 characters")
    private String name;

    @NotBlank(message = "Currency is required")
    private String currency;

    public Customer() {
        this.setPrefix("CUST");
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
