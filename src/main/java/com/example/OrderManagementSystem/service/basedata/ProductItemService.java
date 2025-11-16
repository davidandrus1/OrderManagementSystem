package com.example.OrderManagementSystem.service.basedata;

import com.example.OrderManagementSystem.model.ProductItem;
import com.example.OrderManagementSystem.repository.basedata.CustomerRepository;
import com.example.OrderManagementSystem.repository.basedata.ProductRepository;
import com.example.OrderManagementSystem.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ProductItemService extends BaseService<ProductItem, ProductRepository> {

    public ProductItemService(ProductRepository repository) {
        super(repository);
    }
}
