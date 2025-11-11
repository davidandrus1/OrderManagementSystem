package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.ContractLine;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository

public class ContractLineRepository extends BaseRepository<ContractLine>{
    private int nextId = 1;

    @Override
    protected String getEntityId(ContractLine entity) {
        return entity.id;
    }

    @Override
    public void save(ContractLine entity) {
        if (entity.id == null || entity.id.isEmpty()) {
            entity.id = String.valueOf(nextId++);
        }
        super.save(entity);
    }
}
