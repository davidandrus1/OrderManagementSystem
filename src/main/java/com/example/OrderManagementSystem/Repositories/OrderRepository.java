package com.example.OrderManagementSystem.Repositories;

import com.example.OrderManagementSystem.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByCustomerId(String customerId);

    List<Order> findByNameContainingIgnoreCase(String name, Sort sort);

    List<Order> findByCustomer_Id(String customerId, Sort sort);

    List<Order> findByNameContainingIgnoreCaseAndCustomer_Id(String name, String customerId, Sort sort);
}