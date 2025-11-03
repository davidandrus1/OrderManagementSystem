package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.OrderLine;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository

public class OrderLineRepository {
    private final Map<String, OrderLine> items = new HashMap<>();
    private Long nextId = 1L;

    public OrderLine save(OrderLine item) {
        if (item.id == null) {
            item.id = String.valueOf(nextId++);
        }
        items.put(item.id, item);
        return item;
    }

    public List<OrderLine> findAll() {
        return new ArrayList<>(items.values());
    }

    public Optional<OrderLine> findById(String id) {
        return Optional.ofNullable(items.get(id));
    }

    public void delete(String id) {
        items.remove(id);
    }
}
