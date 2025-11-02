package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Product;
import java.util.*;


import org.springframework.stereotype.Repository;

@Repository

public class ProductRepository {
    private final Map<String, Product> items = new HashMap<>();
    private int nextId = 1;

    public Product save(Product item) {
        if (item.id == null) {
            item.id = String.valueOf(nextId++);
        }
        items.put(item.id, item);
        return item;
    }

    public List<Product> findAll() {
        return new ArrayList<>(items.values());
    }

    public Optional<Product> findById(String id) {
        return Optional.ofNullable(items.get(id));
    }

    public void delete(String id) {
        items.remove(id);
    }
}
