package com.example.OrderManagementSystem.service.documents;

import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.repository.documents.ContractRepository;
import com.example.OrderManagementSystem.repository.documents.OrderRepository;
import com.example.OrderManagementSystem.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContractService extends BaseService<Contract, ContractRepository> {
    public ContractService(ContractRepository repository) {
        super(repository);
    }
}

