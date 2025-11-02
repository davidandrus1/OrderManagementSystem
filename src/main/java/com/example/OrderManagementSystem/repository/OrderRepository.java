package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Order;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository

public class OrderRepository {

    private final Map<String, Order> orderStore = new HashMap<>();

    private int nextId = 1;

    public Order save(Order order) {
        if (order.getId() == null) {
            order.setId(String.valueOf(nextId++)); // convertim int -> String
        }
        orderStore.put(order.getId(), order);
        return order;
    }

    public List<Order> findAll() {
        return new ArrayList<>(orderStore.values());
    }

    public Optional<Order> findById(String id) {
        return Optional.ofNullable(orderStore.get(id));
    }

    public boolean delete(String id) {
        return orderStore.remove(id) != null;
    }


}

