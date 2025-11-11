package com.example.OrderManagementSystem.repository;
import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.model.Order;
import java.util.*;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository extends BaseRepository<Order> {

    @Override
    protected String getEntityId(Order entity) {
        return entity.getId();
    }
}


