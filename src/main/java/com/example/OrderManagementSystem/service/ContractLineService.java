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

    public void save(ContractLine contractLine) {
        repository.save(contractLine);
    }

    public List<ContractLine> getAll() {
        return repository.findAll();
    }

    public ContractLine getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ContractLine with id " + id + " not found"));
    }

    public void delete(String id) {
        repository.findById(id).ifPresent(repository::delete);
    }
}