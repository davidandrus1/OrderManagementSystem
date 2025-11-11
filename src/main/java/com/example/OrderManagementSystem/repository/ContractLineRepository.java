package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.ContractLine;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository

public class ContractLineRepository extends BaseRepository<ContractLine>{

    @Override
    protected String getEntityId(ContractLine entity) {
        return entity.id;
    }

}
