package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Models.Contract;
import com.example.OrderManagementSystem.Repositories.ContractRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService extends BaseService<Contract, ContractRepository> {

    public ContractService(ContractRepository repository) {
        super(repository);
    }

    public List<Contract> findByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId);
    }

    public List<Contract> findByName(String name, Sort sort) {
        return repository.findByNameContainingIgnoreCase(name, sort);
    }

    public List<Contract> findByStatus(String status, Sort sort) {
        return repository.findByStatus(status, sort);
    }

    public List<Contract> findByNameAndStatus(String name, String status, Sort sort) {
        return repository.findByNameContainingIgnoreCaseAndStatus(name, status, sort);
    }
}