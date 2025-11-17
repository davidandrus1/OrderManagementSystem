package com.example.OrderManagementSystem.repository.basedata;

import com.example.OrderManagementSystem.model.SellableItem;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository

public class SellableItemRepository<T extends SellableItem> {
    protected final Map<String, T> items = new HashMap<>();
    protected int nextId = 1;

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
