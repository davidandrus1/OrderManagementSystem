package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    private final ContractRepository repository;

    public ContractService(ContractRepository repository) {
        this.repository = repository;
    }

    public Contract save(Contract contract) {
        validateContract(contract);
        repository.save(contract);
        return contract;
    }

    public List<Contract> getAll() {
        return repository.findAll();
    }

    public Contract getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contract with id " + id + " not found"));
    }

    public Contract update(String id, Contract updatedContract) {
        Contract existingContract = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contract with id " + id + " not found"));

        if (updatedContract.getContractNumber() != null && !updatedContract.getContractNumber().isBlank()) {
            existingContract.setContractNumber(updatedContract.getContractNumber());
        }
        if (updatedContract.getContractTypeId() != null) {
            existingContract.setContractTypeId(updatedContract.getContractTypeId());
        }
        if (updatedContract.getStatus() != null && !updatedContract.getStatus().isBlank()) {
            existingContract.setStatus(updatedContract.getStatus());
        }

        validateContract(existingContract);
        repository.save(existingContract);
        return existingContract;
    }

    public void delete(String id) {
        if (repository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Cannot delete: contract with id " + id + " not found");
        }
        repository.delete(id);
    }

    private void validateContract(Contract contract) {
        if (contract.getId() == null || contract.getId().isBlank()) {
            throw new IllegalArgumentException("Contract ID cannot be empty");
        }
        if (contract.getContractNumber() == null || contract.getContractNumber().isBlank()) {
            throw new IllegalArgumentException("Contract number cannot be empty");
        }
        if (contract.getContractTypeId() == null) {
            throw new IllegalArgumentException("Contract type ID cannot be null");
        }
        if (contract.getStatus() == null || contract.getStatus().isBlank()) {
            throw new IllegalArgumentException("Contract status cannot be empty");
        }
    }
}

