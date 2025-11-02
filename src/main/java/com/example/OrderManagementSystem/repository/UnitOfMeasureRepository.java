package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.UnitOfMeasure;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository

public class UnitOfMeasureRepository {
    private final Map<String, UnitOfMeasure> items = new HashMap<>();
    private int nextId = 1;

    public UnitOfMeasure save(UnitOfMeasure item) {
        if (item.id == null) {
            item.id = String.valueOf(nextId++);
        }
        items.put(item.id, item);
        return item;
    }

    public List<UnitOfMeasure> findAll() {
        return new ArrayList<>(items.values());
    }

    public Optional<UnitOfMeasure> findById(String id) {
        return Optional.ofNullable(items.get(id));
    }

    public void delete(String id) {
        items.remove(id);
    }
}
