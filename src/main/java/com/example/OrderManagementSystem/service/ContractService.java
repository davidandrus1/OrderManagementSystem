package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    private final ContractRepository repository;

    public ContractService() {
        this.repository = new ContractRepository();
    }

    public void save(Contract contract) {
        repository.save(contract);
    }

    public List<Contract> getAll() {
        return repository.findAll();
    }

    public Contract getById(String id) {
        return this.repository
                .findById(id).orElseThrow(() -> new IllegalArgumentException("Contract with id " + id + " not found"));
    }

    public void delete(String id) {
        repository.findById(id).ifPresent(repository::delete);
    }
}

