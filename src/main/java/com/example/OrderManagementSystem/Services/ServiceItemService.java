package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Models.ServiceItem;
import com.example.OrderManagementSystem.Repositories.ServiceItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceItemService extends BaseService<ServiceItem, ServiceItemRepository> {

    public ServiceItemService(ServiceItemRepository repository) {
        super(repository);
    }
}