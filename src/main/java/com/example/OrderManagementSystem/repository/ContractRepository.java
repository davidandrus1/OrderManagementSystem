package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Contract;

import java.util.*;

public class ContractRepository {

    private final Map<Long, Contract> contractStore = new HashMap<>();
    private int nextId = 1;

    public Contract save(Contract contract) {
        if (contract.getId() == null) {
            contract.setId(nextId++);
        }
        contractStore.put(contract.getId(), contract);
        return contract;
    }

    public List<Contract> findAll() {
        return new ArrayList<>(contractStore.values());
    }

    public Optional<Contract> findById(Long id) {
        return Optional.ofNullable(contractStore.get(id));
    }

    public boolean delete(Long id) {
        return contractStore.remove(id) != null;
    }

}