package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Models.ProductItem;
import com.example.OrderManagementSystem.Repositories.ProductItemRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductItemService extends BaseService<ProductItem, ProductItemRepository> {

    public ProductItemService(ProductItemRepository repository) {
        super(repository);
    }

    public List<ProductItem> findByName(String name, Sort sort) {
        return repository.findByNameContainingIgnoreCase(name, sort);
    }

    public List<ProductItem> findByValue(String value, Sort sort) {
        return repository.findByValue(value, sort);
    }

    public List<ProductItem> findByNameAndvalue(String name, String value, Sort sort) {
        return repository.findByNameContainingIgnoreCaseAndValue(name, value, sort);
    }
}