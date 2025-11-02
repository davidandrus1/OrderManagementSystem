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

    public OrderLine save(OrderLine orderLine) {
        validateOrderLine(orderLine);
        return repository.save(orderLine);
    }

    public List<OrderLine> getAll() {
        return repository.findAll();
    }

    public OrderLine getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("OrderLine with id " + id + " not found"));
    }

    public void delete(String id) {
        if (repository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Cannot delete: order line not found");
        }
        repository.delete(id);
    }

    private void validateOrderLine(OrderLine orderLine) {
        if (orderLine.getId() == null || orderLine.getId().isBlank()) {
            throw new IllegalArgumentException("Order ID cannot be empty");
        }
        if (orderLine.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
    }
}