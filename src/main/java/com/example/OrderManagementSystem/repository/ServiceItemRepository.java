package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.model.ServiceItem;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class ServiceItemRepository extends BaseRepository<ServiceItem> {

    @Override
    protected String getEntityId(ServiceItem entity) {
        return entity.getId();
    }
}