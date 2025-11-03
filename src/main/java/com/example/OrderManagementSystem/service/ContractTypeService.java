package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.ContractType;
import com.example.OrderManagementSystem.repository.ContractTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractTypeService {

    private final ContractTypeRepository repository;

    public ContractTypeService(ContractTypeRepository repository) {
        this.repository = repository;
    }

    public ContractType save(ContractType contractType) {
        validateContractType(contractType);
        repository.save(contractType);
        return contractType;
    }

    public List<ContractType> getAll() {
        return repository.findAll();
    }

    public ContractType getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ContractType with id " + id + " not found"));
    }

    public ContractType update(String id, ContractType updatedContractType) {
        ContractType existingType = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ContractType with id " + id + " not found"));

        if (updatedContractType.getName() != null && !updatedContractType.getName().isBlank()) {
            existingType.setName(updatedContractType.getName());
        }
        if (updatedContractType.getType() != null && !updatedContractType.getType().isBlank()) {
            existingType.setType(updatedContractType.getType());
        }

        validateContractType(existingType);
        repository.save(existingType);
        return existingType;
    }

    public void delete(String id) {
        if (repository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Cannot delete: ContractType with id " + id + " not found");
        }
        repository.delete(id);
    }

    private void validateContractType(ContractType contractType) {
        if (contractType.getId() == null || contractType.getId().isBlank()) {
            throw new IllegalArgumentException("ContractType ID cannot be empty");
        }
        if (contractType.getName() == null || contractType.getName().isBlank()) {
            throw new IllegalArgumentException("ContractType name cannot be empty");
        }
        if (contractType.getType() == null || contractType.getType().isBlank()) {
            throw new IllegalArgumentException("ContractType type cannot be empty (must be 'customer' or 'seller')");
        }
    }
}
