package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.ContractLine;
import com.example.OrderManagementSystem.repository.ContractLineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractLineService {

    private final ContractLineRepository repository;

    public ContractLineService(ContractLineRepository repository) {
        this.repository = repository;
    }

    public ContractLine save(ContractLine contractLine) {
        validateContractLine(contractLine);
        return repository.save(contractLine);
    }

    public List<ContractLine> getAll() {
        return repository.findAll();
    }

    public ContractLine getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ContractLine with id " + id + " not found"));
    }

    public void delete(String id) {
        if (repository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Cannot delete: contract line not found");
        }
        repository.delete(id);
    }

    private void validateContractLine(ContractLine contractLine) {
        if (contractLine.getId() == null || contractLine.getId().isBlank()) {
            throw new IllegalArgumentException("Contract ID cannot be empty");
        }
        if (contractLine.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
    }
}