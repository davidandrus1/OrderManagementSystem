package com.example.OrderManagementSystem.service.documents; // Sau .service.documents

import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.repository.documents.OrderRepository; // Noul import
// Importați celelalte Repositories necesare (Customer, OrderLine, etc.)

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    // ... alte Repositories

    // Constructor Injection
    public OrderService(OrderRepository orderRepository /*, ... */) {
        this.orderRepository = orderRepository;
        // ...
    }

    // Metodele CRUD rămân aceleași, deoarece ele apelează doar Repository-ul:
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(String id) {
        return orderRepository.findById(id);
    }

    public Order save(Order order) {
        // Logica de business rămâne aici
         orderRepository.save(order);
         return order;
    }

    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }
}