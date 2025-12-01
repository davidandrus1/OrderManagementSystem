package com.example.OrderManagementSystem.Repositories;

import com.example.OrderManagementSystem.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
