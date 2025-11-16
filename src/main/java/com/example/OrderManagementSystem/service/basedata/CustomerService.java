package com.example.OrderManagementSystem.service.basedata;

import com.example.OrderManagementSystem.model.Customer;
import com.example.OrderManagementSystem.repository.basedata.CustomerRepository;
import com.example.OrderManagementSystem.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends BaseService<Customer, CustomerRepository> {

    public CustomerService(CustomerRepository repository) {
        super(repository);
    }
}


