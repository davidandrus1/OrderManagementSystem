package com.example.OrderManagementSystem.repository;
import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.model.OrderLine;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class OrderLineRepository extends BaseRepository<OrderLine> {

    @Override
    protected String getEntityId(OrderLine entity) {
        return entity.getId();
    }
}
