package com.example.OrderManagementSystem.Models;

import com.example.OrderManagementSystem.Repositories.ContractTypeRepository;
import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer extends BaseModel {

    private String name;
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
