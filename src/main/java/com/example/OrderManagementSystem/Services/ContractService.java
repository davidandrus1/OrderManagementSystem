package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Exceptions.BusinessException;
import com.example.OrderManagementSystem.Models.Contract;
import com.example.OrderManagementSystem.Repositories.ContractRepository;
import org.springframework.stereotype.Service;

@Service
public class ContractService extends BaseService<Contract, ContractRepository> {

    public ContractService(ContractRepository repository) {
        super(repository);
    }

    @Override
    public Contract save(Contract contract) {

        // ✅ VALIDARE 1: ContractType obligatoriu
        if (contract.getContractType() == null) {
            throw new BusinessException("Contract type is mandatory.");
        }

        // ✅ VALIDARE 2: Nume obligatoriu
        if (contract.getName() == null || contract.getName().isBlank()) {
            throw new BusinessException("Contract name cannot be empty.");
        }

        // ✅ VALIDARE 3: Unicitate nume (DOAR la CREATE)
        if (contract.getId() == null &&
                repository.existsByName(contract.getName())) {
            throw new BusinessException("A contract with this name already exists.");
        }

        // ✅ VALIDARE 4: Status permis
        if (!"Active".equals(contract.getStatus()) &&
                !"Down".equals(contract.getStatus())) {
            throw new BusinessException("Invalid contract status.");
        }

        // ✅ TOT OK → salvare
        return super.save(contract);
    }
}
