package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Models.ProductItem;
import com.example.OrderManagementSystem.Repositories.ProductItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductItemService extends BaseService<ProductItem, ProductItemRepository> {

    public ProductItemService(ProductItemRepository repository) {
        super(repository);
    }
}
