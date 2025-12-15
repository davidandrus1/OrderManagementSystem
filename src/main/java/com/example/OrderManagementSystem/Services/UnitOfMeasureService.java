package com.example.OrderManagementSystem.Services;

import com.example.OrderManagementSystem.Models.UnitOfMeasure;
import com.example.OrderManagementSystem.Repositories.UnitOfMeasureRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitOfMeasureService extends BaseService<UnitOfMeasure, UnitOfMeasureRepository> {

    public UnitOfMeasureService(UnitOfMeasureRepository repository) {
        super(repository);
    }

    public List<UnitOfMeasure> findByName(String name, Sort sort) {
        return repository.findByNameContainingIgnoreCase(name, sort);
    }

    public List<UnitOfMeasure> findBySymbol(String symbol, Sort sort) {
        return repository.findBySymbolContainingIgnoreCase(symbol, sort);
    }

    public List<UnitOfMeasure> findByNameAndSymbol(String name, String symbol, Sort sort) {
        return repository.findByNameContainingIgnoreCaseAndSymbolContainingIgnoreCase(name, symbol, sort);
    }
}

