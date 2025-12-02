package com.example.OrderManagementSystem.Repositories;

import com.example.OrderManagementSystem.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}