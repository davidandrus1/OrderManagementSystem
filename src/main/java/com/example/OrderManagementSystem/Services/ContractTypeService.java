package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Models.ContractType;
import com.example.OrderManagementSystem.Repositories.ContractTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class ContractTypeService extends BaseService<ContractType, ContractTypeRepository> {

    public ContractTypeService(ContractTypeRepository repository) {
        super(repository);
    }

}
