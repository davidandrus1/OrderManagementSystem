package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Models.ContractType;
import com.example.OrderManagementSystem.Repositories.ContractTypeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractTypeService extends BaseService<ContractType, ContractTypeRepository> {

    public ContractTypeService(ContractTypeRepository repository) {
        super(repository);
    }

    public List<ContractType> findByName(String name, Sort sort) {
        return repository.findByNameContainingIgnoreCase(name, sort);
    }

    public List<ContractType> findByType(String type, Sort sort) {
        return repository.findByTypeContainingIgnoreCase(type, sort);
    }

    public List<ContractType> findByNameAndType(String name, String type, Sort sort) {
        return repository.findByNameContainingIgnoreCaseAndTypeContainingIgnoreCase(name, type, sort);
    }
}