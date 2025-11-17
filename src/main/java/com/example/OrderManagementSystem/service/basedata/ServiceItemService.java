package com.example.OrderManagementSystem.service.basedata;

import com.example.OrderManagementSystem.model.Customer;
import com.example.OrderManagementSystem.model.ServiceItem;
import com.example.OrderManagementSystem.repository.basedata.CustomerRepository;
import com.example.OrderManagementSystem.repository.basedata.ServiceItemRepository;
import com.example.OrderManagementSystem.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceItemService extends BaseService<ServiceItem, ServiceItemRepository> {

    public ServiceItemService(ServiceItemRepository repository) {
        super(repository);
    }
}