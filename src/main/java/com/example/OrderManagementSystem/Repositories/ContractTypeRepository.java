package com.example.OrderManagementSystem.Repositories;

import com.example.OrderManagementSystem.Models.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractTypeRepository extends JpaRepository<ContractType, String> {
}
