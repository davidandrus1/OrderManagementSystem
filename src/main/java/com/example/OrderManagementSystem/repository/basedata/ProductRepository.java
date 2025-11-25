package com.example.OrderManagementSystem.repository.basedata;

import com.example.OrderManagementSystem.model.ProductItem;


import com.example.OrderManagementSystem.repository.InFileRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository extends InFileRepository<ProductItem> {

    public ProductRepository() {
//        super("data/products.json", ProductItem.class);
        super("src/main/resources/data/products.json", ProductItem.class);
    }
}