package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Models.ServiceItem;
import com.example.OrderManagementSystem.Repositories.ServiceItemRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceItemService extends BaseService<ServiceItem, ServiceItemRepository> {

    public ServiceItemService(ServiceItemRepository repository) {
        super(repository);
    }

    public List<ServiceItem> findByName(String name, Sort sort) {
        return repository.findByNameContainingIgnoreCase(name, sort);
    }

    public List<ServiceItem> findByStatus(String status, Sort sort) {
        return repository.findByStatus(status, sort);
    }

    public List<ServiceItem> findByNameAndStatus(String name, String status, Sort sort) {
        return repository.findByNameContainingIgnoreCaseAndStatus(name, status, sort);
    }
}