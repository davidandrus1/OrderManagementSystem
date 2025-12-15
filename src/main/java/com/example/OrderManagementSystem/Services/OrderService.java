package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Models.Order;
import com.example.OrderManagementSystem.Repositories.OrderRepository;
import org.springframework.data.domain.Sort;
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

    public List<Order> findByName(String name, Sort sort) {
        return repository.findByNameContainingIgnoreCase(name, sort);
    }

    public List<Order> findByCustomer(String customerId, Sort sort) {
        return repository.findByCustomer_Id(customerId, sort);
    }

    public List<Order> findByNameAndCustomer(String name, String customerId, Sort sort) {
        return repository.findByNameContainingIgnoreCaseAndCustomer_Id(name, customerId, sort);
    }
}