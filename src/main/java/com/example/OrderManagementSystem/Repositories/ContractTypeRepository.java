package com.example.OrderManagementSystem.Repositories;

import com.example.OrderManagementSystem.Models.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import java.util.List;

@Repository
public interface ContractTypeRepository extends JpaRepository<ContractType, String> {

    List<ContractType> findByNameContainingIgnoreCase(String name, Sort sort);

    List<ContractType> findByTypeContainingIgnoreCase(String type, Sort sort);

    List<ContractType> findByNameContainingIgnoreCaseAndTypeContainingIgnoreCase(String name, String type, Sort sort);
}