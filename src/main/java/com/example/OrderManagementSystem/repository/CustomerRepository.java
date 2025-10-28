package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Customer;

import java.util.*;

public class CustomerRepository {

    private final Map<String, Customer> customerStore = new HashMap<>();

    public Customer save(Customer customer) {
        if (customer.getId() == null || customer.getId().isEmpty()) {
            customer.setId(UUID.randomUUID().toString());
        }
        customerStore.put(customer.getId(), customer);
        return customer;
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customerStore.values());
    }

    public Optional<Customer> findById(String id) {
        return Optional.ofNullable(customerStore.get(id));
    }

    public boolean delete(String id) {
        return customerStore.remove(id) != null;
    }


}

