package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.Customer;
import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public Optional<Customer> getCustomerById(String id) {
        return repository.findById(id);
    }

    public void addCustomer(Customer customer) {
        repository.save(customer);
    }

    public void deleteCustomer(String id) {
        repository.delete(id);
    }

    public void updateCustomer(String id, Customer updatedCustomer) {
        Optional<Customer> existingCustomer = repository.findById(id);
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            customer.setName(updatedCustomer.getName());
            customer.setCurrency(updatedCustomer.getCurrency());
            customer.setOrders(updatedCustomer.getOrders());
            customer.setContracts(updatedCustomer.getContracts());
            repository.save(customer);
        }
    }


}
