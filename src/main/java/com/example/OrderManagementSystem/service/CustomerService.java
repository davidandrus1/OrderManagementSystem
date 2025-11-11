package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.Customer;
import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer save(Customer customer) {
        repository.save(customer);
        return customer;
    }

    public List<Customer> getAll() {
        return repository.findAll();
    }

    public Customer getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer with id " + id + " not found"));
    }

    public void delete(String id) {
        repository.findById(id).ifPresent(repository::delete);

    }

}

