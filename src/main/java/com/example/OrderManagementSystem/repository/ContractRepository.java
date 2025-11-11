package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Contract;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class ContractRepository {

    private final List<Contract> list = new ArrayList<>();
    private int nextId = 1;

    public void save(Contract contract) {
        this.list.add(contract);
    }

    public List<Contract> findAll() {
        return list;
    }

    public Optional<Contract> findById(int id) {
        return this.list.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    public boolean delete(Contract contract) {
        return this.list.remove(contract);
    }

}