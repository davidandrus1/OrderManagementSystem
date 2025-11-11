package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.model.ContractType;
import com.example.OrderManagementSystem.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    private final ContractRepository repository;

    private int nextId = 1;

    public ContractService() {
        this.repository = new ContractRepository();
    }

    public Contract save(Contract contract) {
        contract.setId(this.nextId++);
        repository.save(contract);
        return contract;
    }

    public List<Contract> getAll() {
        return repository.findAll();
    }

    public Optional<Contract> getById(int id) {
        return this.repository.findById(id);
    }

    public void delete(int id) {
        Optional<Contract> contract = repository.findById(id);
        if (contract.isEmpty()) {
            throw new IllegalArgumentException("Cannot delete: contract with id " + id + " not found");
        }
        repository.delete(contract.get());
    }
}

