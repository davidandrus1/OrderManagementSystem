package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.Customer;
import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        Customer customer = service.getById(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer saved = service.save(customer);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer updatedCustomer) {
        Customer updated = service.update(id, updatedCustomer);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok("Customer with id " + id + " deleted successfully");
    }

    @PostMapping("/{id}/orders")
    public ResponseEntity<String> addOrderToCustomer(@PathVariable String id, @RequestBody Order order) {
        service.addOrderToCustomer(id, order);
        return ResponseEntity.ok("Order added to customer " + id);
    }

    @PostMapping("/{id}/contracts")
    public ResponseEntity<String> addContractToCustomer(@PathVariable String id, @RequestBody Contract contract) {
        service.addContractToCustomer(id, contract);
        return ResponseEntity.ok("Contract added to customer " + id);
    }

    @GetMapping("/{id}/total")
    public ResponseEntity<Double> getCustomerTotal(@PathVariable String id) {
        double total = service.calculateTotalForCustomer(id);
        return ResponseEntity.ok(total);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
