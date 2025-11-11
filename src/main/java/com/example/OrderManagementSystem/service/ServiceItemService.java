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

    public void save(ServiceItem service) {
        repository.save(service);
    }

    public List<ServiceItem> getAll() {
        return repository.findAll();
    }

    public ServiceItem getById(String id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Service with id " + id + " not found"));
    }

    public void delete(String id) {
        repository.findById(id).ifPresent(repository::delete);
    }
}