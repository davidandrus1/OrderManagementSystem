package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.ContractType;
import com.example.OrderManagementSystem.repository.ContractTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractTypeService {

    private final ContractTypeRepository repository;
    private int nextId = 1;

    public ContractTypeService() {
        this.repository = new ContractTypeRepository();
    }

    public ContractType save(ContractType contractType) {
        contractType.setId(this.nextId++);
        repository.save(contractType);
        return contractType;
    }

    public List<ContractType> getAll() {
        return repository.findAll();
    }

    public ContractType getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ContractType with id " + id + " not found"));
    }

    public void delete(int id) {
        Optional<ContractType> contractType = repository.findById(id);
        if (contractType.isEmpty()) {
            throw new IllegalArgumentException("Cannot delete: ContractType with id " + id + " not found");
        }
        repository.delete(contractType.get());
    }

}
