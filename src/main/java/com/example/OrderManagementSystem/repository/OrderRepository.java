package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Order;

import java.util.*;

public class OrderRepository {

    private final Map<Long, Order> orderStore = new HashMap<>();
    private Long nextId = 1L;

    public Order save(Order order) {
        if (order.getId() == null) {
            order.setId(nextId++);
        }
        orderStore.put(order.getId(), order);
        return order;
    }

    public List<Order> findAll() {
        return new ArrayList<>(orderStore.values());
    }

    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orderStore.get(id));
    }

    public boolean delete(Long id) {
        return orderStore.remove(id) != null;
    }


}
