package com.example.OrderManagementSystem.repository;
import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.model.UnitOfMeasure;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class UnitOfMeasureRepository extends BaseRepository<UnitOfMeasure> {

    @Override
    protected String getEntityId(UnitOfMeasure entity) {
        return entity.getId();
    }
}
