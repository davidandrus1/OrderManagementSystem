package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return service.getAllOrders();
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable String id) {
        return service.getOrderById(id);
    }

    @PostMapping
    public void addOrder(@RequestBody Order order) {
        service.addOrder(order);
    }

    @PutMapping("/{id}")
    public void updateOrder(@PathVariable String id, @RequestBody Order updatedOrder) {
        service.updateOrder(id, updatedOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable String id) {
        service.deleteOrder(id);
    }

    @GetMapping("/{id}/total")
    public double getOrderTotal(@PathVariable String id) {
        return service.calculateTotalAmount(id);
    }
}
