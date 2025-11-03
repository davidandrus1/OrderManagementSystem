package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.ContractType;
import com.example.OrderManagementSystem.repository.ContractTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractTypeService {

    private final ContractTypeRepository repository;

    public ContractTypeService(ContractTypeRepository repository) {
        this.repository = repository;
    }

    public List<ContractType> getAllContractTypes() {
        return repository.findAll();
    }

    public Optional<ContractType> getContractTypeById(String id) {
        return repository.findById(id);
    }

    public void addContractType(ContractType contractType) {
        repository.save(contractType);
    }

    public void deleteContractType(String id) {
        repository.delete(id);
    }

    public void updateContractType(String id, ContractType updatedContractType) {
        Optional<ContractType> existingType = repository.findById(id);
        if (existingType.isPresent()) {
            ContractType type = existingType.get();
            type.setName(updatedContractType.getName());
            type.setType(updatedContractType.getType());
            repository.save(type);
        }
    }
}
