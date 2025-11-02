package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.ServiceItem;
import java.util.*;

public class ServiceItemRepository {
    private final Map<String, ServiceItem> items = new HashMap<>();
    private int nextId = 1;

    public ServiceItem save(ServiceItem item) {
        if (item.id == null) {
            item.id = String.valueOf(nextId++);
        }
        items.put(item.id, item);
        return item;
    }

    public List<ServiceItem> findAll() {
        return new ArrayList<>(items.values());
    }

    public Optional<ServiceItem> findById(String id) {
        return Optional.ofNullable(items.get(id));
    }

    public void delete(String id) {
        items.remove(id);
    }
}
