package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.ServiceItem;
import com.example.OrderManagementSystem.repository.ServiceItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceItemService {

    private final ServiceItemRepository repository;

    public ServiceItemService(ServiceItemRepository repository) {
        this.repository = repository;
    }

    public ServiceItem save(ServiceItem service) {
        validateService(service);
        return repository.save(service);
    }

    public List<ServiceItem> getAll() {
        return repository.findAll();
    }

    public ServiceItem getById(String id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Service with id " + id + " not found"));
    }

    public void delete(String id) {
        if (repository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Cannot delete: service not found");
        }
        repository.delete(id);
    }

    private void validateService(ServiceItem service) {
        if (service.getName() == null || service.getName().isBlank()) {
            throw new IllegalArgumentException("Service name cannot be empty");
        }

    }
}