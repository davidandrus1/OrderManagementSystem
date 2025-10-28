package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Customer;

import java.util.*;

public class CustomerRepository {

    private final Map<Long, Customer> customerStore = new HashMap<>();
    private Long nextId = 1L;

    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            customer.setId(nextId++);
        }
        customerStore.put(customer.getId(), customer);
        return customer;
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customerStore.values());
    }

    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(customerStore.get(id));
    }

    public boolean delete(Long id) {
        return customerStore.remove(id) != null;
    }


}
