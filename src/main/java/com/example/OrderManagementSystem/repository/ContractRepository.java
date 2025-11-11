package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Contract;
import org.springframework.stereotype.Repository;

@Repository
public class ContractRepository extends BaseRepository<Contract> {

    @Override
    protected String getEntityId(Contract entity) {
        return entity.getId();
    }
}