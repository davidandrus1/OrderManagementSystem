package com.example.OrderManagementSystem.Repositories;

import com.example.OrderManagementSystem.Models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, String> {

    List<Contract> findByCustomerId(String customerId);
}