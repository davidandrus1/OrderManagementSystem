package com.example.OrderManagementSystem.service.documents;

import com.example.OrderManagementSystem.model.Customer;
import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.repository.basedata.CustomerRepository;
import com.example.OrderManagementSystem.repository.documents.OrderRepository;
import com.example.OrderManagementSystem.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class OrderService extends BaseService<Order, OrderRepository> {

    public OrderService(OrderRepository repository) {
        super(repository);
    }
}

