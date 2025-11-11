package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Customer;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository

public class CustomerRepository {

    private final List<Customer> list = new ArrayList<>();

    public void save(Customer customer) {
        list.add(customer);
    }

    public List<Customer> findAll() {
        return list;
    }

    public Optional<Customer> findById(int id) {
        return this.list.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    public boolean delete(Customer customer) {
        return this.list.remove(customer);
    }
}

