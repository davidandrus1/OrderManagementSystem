package com.example.OrderManagementSystem.repository.basedata;

import com.example.OrderManagementSystem.model.Customer;
import com.example.OrderManagementSystem.repository.InFileRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository extends InFileRepository<Customer> {

    public CustomerRepository() {
        super("OrderManagementSystem/src/main/data/customers.json", Customer.class);
    }
}