package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Models.SellableItem;
import com.example.OrderManagementSystem.Repositories.SellableItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellableItemService {

    private final SellableItemRepository repository;

    public SellableItemService(SellableItemRepository repository) {
        this.repository = repository;
    }

    public List<SellableItem> findAll() {
        return repository.findAll();
    }

    public SellableItem findById(String id) {
        return repository.findById(id).orElse(null);
    }
}