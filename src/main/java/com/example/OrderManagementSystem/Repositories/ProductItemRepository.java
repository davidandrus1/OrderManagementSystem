package com.example.OrderManagementSystem.Repositories;

import com.example.OrderManagementSystem.Models.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, String> {
}