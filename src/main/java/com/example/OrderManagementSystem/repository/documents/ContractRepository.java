package com.example.OrderManagementSystem.repository.documents;

import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.repository.BaseRepository;
import com.example.OrderManagementSystem.repository.InFileRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ContractRepository extends InFileRepository<Contract> {
    public ContractRepository() {
        super("src/main/resources/data/contracts.json", Contract.class);
    }

}