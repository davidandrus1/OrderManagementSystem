package com.example.OrderManagementSystem.Repositories;

import com.example.OrderManagementSystem.Models.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import java.util.List;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, String> {

    List<ProductItem> findByNameContainingIgnoreCase(String name, Sort sort);

    List<ProductItem> findByValue(Double value, Sort sort);

    List<ProductItem> findByNameContainingIgnoreCaseAndValue(String name, Double value, Sort sort);
}