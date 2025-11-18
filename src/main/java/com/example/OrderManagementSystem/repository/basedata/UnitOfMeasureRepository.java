package com.example.OrderManagementSystem.repository.basedata;
import com.example.OrderManagementSystem.model.UnitOfMeasure;

import com.example.OrderManagementSystem.repository.InFileRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UnitOfMeasureRepository extends InFileRepository<UnitOfMeasure> {

    public UnitOfMeasureRepository() {
        super("data/unit-of-measure.json", UnitOfMeasure.class);
    }
}