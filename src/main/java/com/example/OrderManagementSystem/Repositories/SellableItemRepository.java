package com.example.OrderManagementSystem.Repositories;

import com.example.OrderManagementSystem.Models.SellableItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellableItemRepository extends JpaRepository<SellableItem, String> {
}