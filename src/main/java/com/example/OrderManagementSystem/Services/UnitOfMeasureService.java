package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Models.UnitOfMeasure;
import com.example.OrderManagementSystem.Repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

@Service
public class UnitOfMeasureService extends BaseService<UnitOfMeasure, UnitOfMeasureRepository> {

    public UnitOfMeasureService(UnitOfMeasureRepository repository) {
        super(repository);
    }

}
