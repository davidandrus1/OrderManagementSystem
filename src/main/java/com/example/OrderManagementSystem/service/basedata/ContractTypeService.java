package com.example.OrderManagementSystem.service.basedata;

import com.example.OrderManagementSystem.model.ContractType;
import com.example.OrderManagementSystem.model.Customer;
import com.example.OrderManagementSystem.repository.basedata.ContractTypeRepository;
import com.example.OrderManagementSystem.repository.basedata.CustomerRepository;
import com.example.OrderManagementSystem.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ContractTypeService extends BaseService<ContractType, ContractTypeRepository> {

    public ContractTypeService(ContractTypeRepository repository) {
        super(repository);
    }

}