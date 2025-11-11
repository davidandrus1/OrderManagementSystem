package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.Product;
import com.example.OrderManagementSystem.model.ServiceItem;
import com.example.OrderManagementSystem.model.SellableItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellableItemService {

    private final ProductService productService;
    private final ServiceItemService serviceItemService;

    public SellableItemService(ProductService productService, ServiceItemService serviceItemService) {
        this.productService = productService;
        this.serviceItemService = serviceItemService;
    }

    public List<SellableItem> getAll() {
        List<SellableItem> allItems = new ArrayList<>();
        allItems.addAll(productService.getAll());
        allItems.addAll(serviceItemService.getAll());
        return allItems;
    }

    public SellableItem getById(String id) {
        Optional<Product> productOpt = productService.getById(id);
        if (productOpt.isPresent()) {
            return productOpt.get();
        }

        ServiceItem serviceItem = serviceItemService.getAll().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("SellableItem with id " + id + " not found"));

        return serviceItem;
    }
}
