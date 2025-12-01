package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Models.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// BaseService.java
public abstract class BaseService<MODEL extends BaseModel, REPOSITORY extends JpaRepository<MODEL, String>> {

    protected final REPOSITORY repository;

    public BaseService(REPOSITORY repository) {
        this.repository = repository;
    }

    public MODEL save(MODEL entity) {
        if (entity.getId() == null || entity.getId().isEmpty()) {
            // Folosim prefix-ul din entitatea care vine ca parametru
            entity.setId(generateNextId(entity.getPrefix()));
        }
        return repository.save(entity);
    }

    private String generateNextId(String prefix) {
        List<MODEL> all = repository.findAll();

        int maxNumber = all.stream()
                .map(BaseModel::getId)
                .filter(id -> id != null && id.startsWith(prefix))
                .map(id -> id.substring(prefix.length() + 1))
                .mapToInt(numStr -> {
                    try {
                        return Integer.parseInt(numStr);
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                })
                .max()
                .orElse(0);

        return String.format("%s-%06d", prefix, maxNumber + 1);
    }

    public List<MODEL> findAll() {
        return repository.findAll();
    }

    public MODEL findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}