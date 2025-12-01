package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Models.Order;
import com.example.OrderManagementSystem.Repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends BaseService<Order, OrderRepository> {

    public OrderService(OrderRepository repository) {
        super(repository);
    }
}