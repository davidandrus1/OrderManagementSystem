package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.OrderLine;
import com.example.OrderManagementSystem.repository.OrderLineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineService {

    private final OrderLineRepository repository;

    public OrderLineService(OrderLineRepository repository) {
        this.repository = repository;
    }

    public void save(OrderLine orderLine) {
        repository.save(orderLine);
    }

    public List<OrderLine> getAll() {
        return repository.findAll();
    }

    public OrderLine getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("OrderLine with id " + id + " not found"));
    }

    public void delete(String id) {
        repository.findById(id).ifPresent(repository::delete);
    }

}