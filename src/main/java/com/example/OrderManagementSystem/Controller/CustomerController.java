package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.Customer;
import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    // GET: toți clienții
    @GetMapping
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }

    // GET: client după ID
    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable String id) {
        return service.getCustomerById(id);
    }

    // POST: adaugă un client nou
    @PostMapping
    public void addCustomer(@RequestBody Customer customer) {
        service.addCustomer(customer);
    }

    // PUT: actualizează un client
    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable String id, @RequestBody Customer updatedCustomer) {
        service.updateCustomer(id, updatedCustomer);
    }

    // DELETE: șterge un client
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable String id) {
        service.deleteCustomer(id);
    }

    // POST: adaugă o comandă unui client
    @PostMapping("/{id}/orders")
    public void addOrderToCustomer(@PathVariable String id, @RequestBody Order order) {
        service.addOrderToCustomer(id, order);
    }

    // POST: adaugă un contract unui client
    @PostMapping("/{id}/contracts")
    public void addContractToCustomer(@PathVariable String id, @RequestBody Contract contract) {
        service.addContractToCustomer(id, contract);
    }
}
