package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Models.OrderLine;
import com.example.OrderManagementSystem.Repositories.OrderLineRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderLineService extends BaseService<OrderLine, OrderLineRepository> {

    public OrderLineService(OrderLineRepository repository) {
        super(repository);
    }
}