package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.SellableItem;
import java.util.*;

public abstract class SellableItemRepository<T extends SellableItem> {
    protected final Map<String, T> items = new HashMap<>();
    protected Long nextId = 1L;

    public T save(T item) {
        if (item.getId() == null) {
            item.setId(String.valueOf(nextId++));
        }
        items.put(item.id, item);
        return item;
    }

    public List<T> findAll() {
        return new ArrayList<>(items.values());
    }

    public Optional<T> findById(Long id) {
        return Optional.ofNullable(items.get(id));
    }

    public void delete(Long id) {
        items.remove(id);
    }
}
