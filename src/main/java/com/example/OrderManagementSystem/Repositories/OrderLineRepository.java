package com.example.OrderManagementSystem.Repositories;

import com.example.OrderManagementSystem.Models.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, String> {
}




