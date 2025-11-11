package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.model.Product;
import java.util.*;


import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository extends BaseRepository<Contract> {

    @Override
    protected String getEntityId(Contract entity) {
        return entity.getId();
    }
}
