package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public void save(Order order) {
        repository.save(order);
    }

    public List<Order> getAll() {
        return repository.findAll();
    }

    public Optional<Order> getById(String id) {
        return repository.findById(id);
    }

    public void delete(String id) {
        repository.findById(id).ifPresent(repository::delete);
    }
}

