package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.ContractType;

import java.util.*;

public class ContractTypeRepository {

    private final Map<String, ContractType> contractTypeStore = new HashMap<>();


    public ContractType save(ContractType contractType) {
        if (contractType.getId() == null || contractType.getId().isEmpty()) {
            contractType.setId(UUID.randomUUID().toString());
        }
        contractTypeStore.put(contractType.getId(), contractType);
        return contractType;
    }

    public List<ContractType> findAll() {
        return new ArrayList<>(contractTypeStore.values());
    }

    public Optional<ContractType> findById(String id) {
        return Optional.ofNullable(contractTypeStore.get(id));
    }

    public boolean delete(String id) {
        return contractTypeStore.remove(id) != null;
    }


}
