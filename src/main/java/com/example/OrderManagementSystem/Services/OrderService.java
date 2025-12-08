package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Models.Order;
import com.example.OrderManagementSystem.Repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService extends BaseService<Order, OrderRepository> {

    public OrderService(OrderRepository repository) {
        super(repository);
    }

    public List<Order> findByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId);
    }
}