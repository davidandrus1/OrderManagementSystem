package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.model.ServiceItem;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class ServiceItemRepository extends BaseRepository<Contract> {

    @Override
    protected String getEntityId(Contract entity) {
        return entity.getId();
    }
}