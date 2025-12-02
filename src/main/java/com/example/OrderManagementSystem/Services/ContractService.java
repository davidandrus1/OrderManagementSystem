package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Models.Contract;
import com.example.OrderManagementSystem.Repositories.ContractRepository;
import org.springframework.stereotype.Service;

@Service
public class ContractService extends BaseService<Contract, ContractRepository> {

    public ContractService(ContractRepository repository) {
        super(repository);
    }
}