package com.example.OrderManagementSystem.service.documents; // Sau .service.documents

import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.repository.documents.OrderRepository;


import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
     //model pentru celelalte
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository /*, ... */) {
        this.orderRepository = orderRepository;

    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(String id) {
        return orderRepository.findById(id);
    }

    public Order save(Order order) {

         orderRepository.save(order);
         return order;
    }

    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }
}