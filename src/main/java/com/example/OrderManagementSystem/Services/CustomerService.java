package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Models.Customer;
import com.example.OrderManagementSystem.Repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends BaseService<Customer, CustomerRepository> {

    public CustomerService(CustomerRepository repository) {
        super(repository);
    }
}