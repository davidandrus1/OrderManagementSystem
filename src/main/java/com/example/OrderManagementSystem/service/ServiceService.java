package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.Service;
import com.example.OrderManagementSystem.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    private final ServiceRepository repository;

    public ServiceService(ServiceRepository repository) {
        this.repository = repository;
    }

    public Service save(Service service) {
        validateService(service);
        return repository.save(service);
    }

    public List<Service> getAll() {
        return repository.findAll();
    }

    public Service getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service with id " + id + " not found"));
    }

    public void delete(String id) {
        if (repository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Cannot delete: service not found");
        }
        repository.delete(id);
    }

    private void validateService(Service service) {
        if (service.getName() == null || service.getName().isBlank()) {
            throw new IllegalArgumentException("Service name cannot be empty");
        }

    }
}