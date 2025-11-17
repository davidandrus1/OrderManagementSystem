package com.example.OrderManagementSystem.service.basedata;

import com.example.OrderManagementSystem.model.Customer;
import com.example.OrderManagementSystem.model.UnitOfMeasure;
import com.example.OrderManagementSystem.repository.basedata.CustomerRepository;
import com.example.OrderManagementSystem.repository.basedata.UnitOfMeasureRepository;
import com.example.OrderManagementSystem.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitOfMeasureService extends BaseService<UnitOfMeasure, UnitOfMeasureRepository> {

    public UnitOfMeasureService(UnitOfMeasureRepository repository) {
        super(repository);
    }
}
