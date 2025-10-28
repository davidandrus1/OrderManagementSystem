package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Service;
import java.util.*;

public class ServiceRepository {
    private final Map<String, Service> items = new HashMap<>();
    private Long nextId = 1L;

    public Service save(Service item) {
        if (item.id == null) {
            item.id = String.valueOf(nextId++);
        }
        items.put(item.id, item);
        return item;
    }

    public List<Service> findAll() {
        return new ArrayList<>(items.values());
    }

    public Optional<Service> findById(String id) {
        return Optional.ofNullable(items.get(id));
    }

    public void delete(String id) {
        items.remove(id);
    }
}
