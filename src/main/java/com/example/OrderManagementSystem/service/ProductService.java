package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.Product;
import com.example.OrderManagementSystem.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Optional<Product> getById(String id) {
        return repository.findById(id);

    }

    public void delete(String id) {
        if (repository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Cannot delete: product not found");
        }
        repository.delete(id);
    }


}
