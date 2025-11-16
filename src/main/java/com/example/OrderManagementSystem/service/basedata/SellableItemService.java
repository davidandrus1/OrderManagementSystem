package com.example.OrderManagementSystem.service.basedata;

import com.example.OrderManagementSystem.model.ProductItem;
import com.example.OrderManagementSystem.model.ServiceItem;
import com.example.OrderManagementSystem.model.SellableItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellableItemService {

    private final ProductItemService productService;
    private final ServiceItemService serviceItemService;

    public SellableItemService(ProductItemService productService, ServiceItemService serviceItemService) {
        this.productService = productService;
        this.serviceItemService = serviceItemService;
    }

    public List<SellableItem> getAll() {
        List<SellableItem> allItems = new ArrayList<>();
        allItems.addAll(productService.findAll());
        allItems.addAll(serviceItemService.findAll());
        return allItems;
    }

    public SellableItem findById(String id) {
        Optional<ProductItem> productOpt = productService.findById(id);
        if (productOpt.isPresent()) {
            return productOpt.get();
        }

        ServiceItem serviceItem = serviceItemService.findAll().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("SellableItem with id " + id + " not found"));

        return serviceItem;
    }
}
