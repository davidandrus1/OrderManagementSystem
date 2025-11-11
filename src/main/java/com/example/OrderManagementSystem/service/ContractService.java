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
//        validateContract(contract);
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

//    public Contract update(String id, Contract updatedContract) {
//        Contract existingContract = repository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Contract with id " + id + " not found"));
//
//        if (updatedContract.getContractNumber() != null && !updatedContract.getContractNumber().isBlank()) {
//            existingContract.setContractNumber(updatedContract.getContractNumber());
//        }
//        if (updatedContract.getContractTypeId() != null) {
//            existingContract.setContractTypeId(updatedContract.getContractTypeId());
//        }
//        if (updatedContract.getStatus() != null && !updatedContract.getStatus().isBlank()) {
//            existingContract.setStatus(updatedContract.getStatus());
//        }
//
//        validateContract(existingContract);
//        repository.save(existingContract);
//        return existingContract;
//    }

    public void delete(int id) {
        Optional<Contract> contract = repository.findById(id);
        if (contract.isEmpty()) {
            throw new IllegalArgumentException("Cannot delete: contract with id " + id + " not found");
        }
        repository.delete(contract.get());
    }
//
//    private void validateContract(Contract contract) {
//        if (contract.getId() == null || contract.getId().isBlank()) {
//            throw new IllegalArgumentException("Contract ID cannot be empty");
//        }
//        if (contract.getContractNumber() == null || contract.getContractNumber().isBlank()) {
//            throw new IllegalArgumentException("Contract number cannot be empty");
//        }
//        if (contract.getContractTypeId() == null) {
//            throw new IllegalArgumentException("Contract type ID cannot be null");
//        }
//        if (contract.getStatus() == null || contract.getStatus().isBlank()) {
//            throw new IllegalArgumentException("Contract status cannot be empty");
//        }
//    }
}

