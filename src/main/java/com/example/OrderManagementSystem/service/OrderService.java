package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order save(Order order) {
        repository.save(order);
        return order;
    }

    public List<Order> getAll() {
        return repository.findAll();
    }

    public Optional<Order> getById(String id) {
        return repository.findById(id);
    }

    public void delete(String id) {
        if (repository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Cannot delete: order with id " + id + " not found");
        }
        repository.delete(id);
    }
}

