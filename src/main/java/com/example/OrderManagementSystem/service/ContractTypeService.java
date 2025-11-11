package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.ContractType;
import com.example.OrderManagementSystem.repository.ContractTypeRepository;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ContractTypeService {

    private final ContractTypeRepository repository;

    public ContractTypeService() {
        this.repository = new ContractTypeRepository();
    }

    public void save(ContractType contractType) {
        repository.save(contractType);
    }

    public List<ContractType> getAll() {
        return repository.findAll();
    }

    public Optional<ContractType> getById(String id) {
        return repository.findById(id);
    }

    public void delete(String id) {
        repository.findById(id).ifPresent(repository::delete);
    }
}
