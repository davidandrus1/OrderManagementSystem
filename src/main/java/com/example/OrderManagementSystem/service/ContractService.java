package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    private final ContractRepository repository;

    public ContractService(ContractRepository repository) {
        this.repository = repository;
    }

    public List<Contract> getAllContracts() {
        return repository.findAll();
    }

    public Optional<Contract> getContractById(String id) {
        return repository.findById(id);
    }

    public void addContract(Contract contract) {
        repository.save(contract);
    }

    public void deleteContract(String id) {
        repository.delete(id);
    }

    public void updateContract(String id, Contract updatedContract) {
        Optional<Contract> existingContract = repository.findById(id);
        if (existingContract.isPresent()) {
            Contract contract = existingContract.get();
            contract.setContractNumber(updatedContract.getContractNumber());
            contract.setContractTypeId(updatedContract.getContractTypeId());
            contract.setStatus(updatedContract.getStatus());
            repository.save(contract);
        }
    }
}
