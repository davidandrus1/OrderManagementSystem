package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.UnitOfMeasure;
import com.example.OrderManagementSystem.repository.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitOfMeasureService {

    private final UnitOfMeasureRepository repository;

    public UnitOfMeasureService(UnitOfMeasureRepository repository) {
        this.repository = repository;
    }

    public UnitOfMeasure save(UnitOfMeasure unitOfMeasure) {
        validateUnitOfMeasure(unitOfMeasure);
        return repository.save(unitOfMeasure);
    }

    public List<UnitOfMeasure> getAll() {
        return repository.findAll();
    }

    public UnitOfMeasure getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("UnitOfMeasure with id " + id + " not found"));
    }

    public void delete(String id) {
        if (repository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Cannot delete: unit of measure not found");
        }
        repository.delete(id);
    }

    private void validateUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        if (unitOfMeasure.getName() == null || unitOfMeasure.getName().isBlank()) {
            throw new IllegalArgumentException("Unit of measure name cannot be empty");
        }
        if (unitOfMeasure.getCode() == null || unitOfMeasure.getCode().isBlank()) {
            throw new IllegalArgumentException("Unit of measure code cannot be empty");
        }
    }
}