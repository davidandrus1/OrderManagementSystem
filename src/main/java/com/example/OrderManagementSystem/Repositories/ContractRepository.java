package com.example.OrderManagementSystem.Repositories;

import com.example.OrderManagementSystem.Models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, String> {

    List<Contract> findByCustomerId(String customerId);

    List<Contract> findByNameContainingIgnoreCase(String name, Sort sort);

    List<Contract> findByStatus(String status, Sort sort);

    List<Contract> findByNameContainingIgnoreCaseAndStatus(String name, String status, Sort sort);
}