package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.SellableItem;
import com.example.OrderManagementSystem.repository.SellableItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellableItemService {

    private final SellableItemRepository<SellableItem> repository;

    public SellableItemService(SellableItemRepository<SellableItem> repository) {
        this.repository = repository;
    }

    public SellableItem save(SellableItem sellableItem) {
        validateSellableItem(sellableItem);
        return repository.save(sellableItem);
    }

    public List<SellableItem> getAll() {
        return repository.findAll();
    }

    public SellableItem getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SellableItem with id " + id + " not found"));
    }

    public void delete(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Cannot delete: sellable item not found");
        }
        repository.delete(id);
    }

    private void validateSellableItem(SellableItem sellableItem) {
        if (sellableItem.getName() == null || sellableItem.getName().isBlank()) {
            throw new IllegalArgumentException("Sellable item name cannot be empty");
        }
    }
}