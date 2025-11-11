package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.UnitOfMeasure;
import com.example.OrderManagementSystem.repository.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitOfMeasureService {

    private final UnitOfMeasureRepository repository;

    public UnitOfMeasureService(UnitOfMeasureRepository repository) {
        this.repository = repository;
    }

    public void save(UnitOfMeasure unitOfMeasure) {
        repository.save(unitOfMeasure);
    }

    public List<UnitOfMeasure> getAll() {
        return repository.findAll();
    }

    public Optional<UnitOfMeasure> getById(String id) {
        return repository.findById(id);
    }

    public void delete(String id) {
        repository.findById(id).ifPresent(repository::delete);
    }
}