package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Models.Customer;
import com.example.OrderManagementSystem.Repositories.CustomerRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService extends BaseService<Customer, CustomerRepository> {

    public CustomerService(CustomerRepository repository) {
        super(repository);
    }

    public List<Customer> findByName(String name, Sort sort) {
        return repository.findByNameContainingIgnoreCase(name, sort);
    }

    public List<Customer> findByCurrency(String currency, Sort sort) {
        return repository.findByCurrency(currency, sort);
    }

    public List<Customer> findByNameAndCurrency(String name, String currency, Sort sort) {
        return repository.findByNameContainingIgnoreCaseAndCurrency(name, currency, sort);
    }
}