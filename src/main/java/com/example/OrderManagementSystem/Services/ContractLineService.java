package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Models.ContractLine;
import com.example.OrderManagementSystem.Repositories.ContractLineRepository;
import org.springframework.stereotype.Service;

@Service
public class ContractLineService extends BaseService<ContractLine, ContractLineRepository> {

    public ContractLineService(ContractLineRepository repository) {
        super(repository);
    }
}