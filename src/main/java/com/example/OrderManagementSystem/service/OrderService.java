package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order save(Order order) {
        validateOrder(order);
        repository.save(order);
        return order;
    }

    public List<Order> getAll() {
        return repository.findAll();
    }

    public Order getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order with id " + id + " not found"));
    }

    public void delete(String id) {
        if (repository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Cannot delete: order with id " + id + " not found");
        }
        repository.delete(id);
    }

    public Order update(String id, Order updatedOrder) {
        Order existingOrder = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order with id " + id + " not found"));

        if (updatedOrder.getOrderNumber() != null && !updatedOrder.getOrderNumber().isBlank()) {
            existingOrder.setOrderNumber(updatedOrder.getOrderNumber());
        }
        if (updatedOrder.getTotalAmount() != null) {
            existingOrder.setTotalAmount(updatedOrder.getTotalAmount());
        }
        if (updatedOrder.getContractId() != null) {
            existingOrder.setContractId(updatedOrder.getContractId());
        }

        validateOrder(existingOrder);
        repository.save(existingOrder);
        return existingOrder;
    }

    private void validateOrder(Order order) {
        if (order.getOrderNumber() == null || order.getOrderNumber().isBlank()) {
            throw new IllegalArgumentException("Order number cannot be empty");
        }
        if (order.getTotalAmount() == null) {
            throw new IllegalArgumentException("Total amount cannot be null");
        }
        if (order.getTotalAmount() < 0) {
            throw new IllegalArgumentException("Total amount cannot be negative");
        }
        if (order.getContractId() == null) {
            throw new IllegalArgumentException("Contract ID cannot be null");
        }
    }
}

