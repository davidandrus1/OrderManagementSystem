package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.ContractLine;
import java.util.*;

public class ContractLineRepository {
    private final Map<String, ContractLine> items = new HashMap<>();
    private int nextId = 1;

    public ContractLine save(ContractLine item) {
        if (item.id == null) {
            item.id = String.valueOf(nextId++);
        }
        items.put(item.id, item);
        return item;
    }

    public List<ContractLine> findAll() {
        return new ArrayList<>(items.values());
    }

    public Optional<ContractLine> findById(String id) {
        return Optional.ofNullable(items.get(id));
    }

    public void delete(String id) {
        items.remove(id);
    }
}
