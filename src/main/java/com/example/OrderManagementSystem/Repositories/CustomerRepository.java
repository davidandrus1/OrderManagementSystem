package com.example.OrderManagementSystem.Repositories;

import com.example.OrderManagementSystem.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    List<Customer> findByNameContainingIgnoreCase(String name, Sort sort);

    List<Customer> findByCurrency(String currency, Sort sort);

    List<Customer> findByNameContainingIgnoreCaseAndCurrency(String name, String currency, Sort sort);
}