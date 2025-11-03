package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Optional<Order> getOrderById(String id) {
        return repository.findById(id);
    }

    public void addOrder(Order order) {
        repository.save(order);
    }

    public void deleteOrder(String id) {
        repository.delete(id);
    }

    public void updateOrder(String id, Order updatedOrder) {
        Optional<Order> existingOrder = repository.findById(id);
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setOrderNumber(updatedOrder.getOrderNumber());
            order.setTotalAmount(updatedOrder.getTotalAmount());
            order.setContractId(updatedOrder.getContractId());
            repository.save(order);
        }
    }


}
