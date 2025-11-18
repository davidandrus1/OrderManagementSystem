package com.example.OrderManagementSystem.repository.basedata;

import com.example.OrderManagementSystem.model.ProductItem;
import com.example.OrderManagementSystem.model.ServiceItem;
import com.example.OrderManagementSystem.repository.InFileRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceItemRepository extends InFileRepository<ServiceItem> {
    public ServiceItemRepository() {
        super("data/service-items.json", ServiceItem.class);
    }
}