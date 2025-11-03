package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.Customer;
import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer save(Customer customer) {
        validateCustomer(customer);
        repository.save(customer);
        return customer;
    }

    public List<Customer> getAll() {
        return repository.findAll();
    }

    public Customer getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer with id " + id + " not found"));
    }

    public Customer update(String id, Customer updatedCustomer) {
        Customer existingCustomer = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer with id " + id + " not found"));

        if (updatedCustomer.getName() != null && !updatedCustomer.getName().isBlank()) {
            existingCustomer.setName(updatedCustomer.getName());
        }
        if (updatedCustomer.getCurrency() != null && !updatedCustomer.getCurrency().isBlank()) {
            existingCustomer.setCurrency(updatedCustomer.getCurrency());
        }
        if (updatedCustomer.getOrders() != null) {
            existingCustomer.setOrders(updatedCustomer.getOrders());
        }
        if (updatedCustomer.getContracts() != null) {
            existingCustomer.setContracts(updatedCustomer.getContracts());
        }

        validateCustomer(existingCustomer);
        repository.save(existingCustomer);
        return existingCustomer;
    }

    public void delete(String id) {
        if (repository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Cannot delete: customer with id " + id + " not found");
        }
        repository.delete(id);
    }

    public void addOrderToCustomer(String customerId, Order order) {
        Customer customer = getById(customerId);
        customer.getOrders().add(order);
        repository.save(customer);
    }

    public void addContractToCustomer(String customerId, Contract contract) {
        Customer customer = getById(customerId);
        customer.getContracts().add(contract);
        repository.save(customer);
    }

    public double calculateTotalForCustomer(String customerId) {
        Customer customer = getById(customerId);
        return customer.getOrders().stream()
                .mapToDouble(order -> order.getTotalAmount() != null ? order.getTotalAmount() : 0.0)
                .sum();
    }

    private void validateCustomer(Customer customer) {
        if (customer.getId() == null || customer.getId().isBlank()) {
            throw new IllegalArgumentException("Customer ID cannot be empty");
        }
        if (customer.getName() == null || customer.getName().isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
        if (customer.getCurrency() == null || customer.getCurrency().isBlank()) {
            throw new IllegalArgumentException("Customer currency cannot be empty");
        }
    }
}
