package com.example.OrderManagementSystem.repository.basedata;
import com.example.OrderManagementSystem.model.ContractType;

import com.example.OrderManagementSystem.model.Customer;
import com.example.OrderManagementSystem.repository.BaseRepository;
import com.example.OrderManagementSystem.repository.InFileRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ContractTypeRepository extends InFileRepository<ContractType> {

    public ContractTypeRepository() {
        super("OrderManagementSystem/src/main/data/contract-types.json", ContractType.class);
    }
}