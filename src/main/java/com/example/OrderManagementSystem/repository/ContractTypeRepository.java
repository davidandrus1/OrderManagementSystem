package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.ContractType;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository

public class ContractTypeRepository {

    private final List<ContractType> list = new ArrayList<>();

    public void save(ContractType contractType) {
        this.list.add(contractType);
    }

    public List<ContractType> findAll() {
        return this.list;
    }

    public Optional<ContractType> findById(int id) {
        return this.list.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    public boolean delete(ContractType contractType) {
        return this.list.remove(contractType);
    }
}
