package com.example.OrderManagementSystem.repository;
import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.model.ContractType;
import java.util.*;
import org.springframework.stereotype.Repository;

@Repository

public class ContractTypeRepository extends BaseRepository<Contract> {

    @Override
    protected String getEntityId(Contract entity) {
        return entity.getId();
    }
}
