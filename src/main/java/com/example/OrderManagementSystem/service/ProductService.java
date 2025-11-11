package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.Product;
import com.example.OrderManagementSystem.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void save(Product product) {
        repository.save(product);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Optional<Product> getById(String id) {
        return repository.findById(id);
    }

    public void delete(String id) {
        repository.findById(id).ifPresent(repository::delete);
    }
}
