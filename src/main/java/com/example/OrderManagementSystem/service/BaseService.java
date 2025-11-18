package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.BaseModel;
import com.example.OrderManagementSystem.repository.InFileRepository;
import com.example.OrderManagementSystem.repository.RepositoryInterface;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<BM extends BaseModel, RI extends InFileRepository<BM>>  implements ServiceInterface<BM> {

    protected final RI repository;

    public BaseService(RI repository) {
        this.repository = repository;
    }

    @Override
    public List<BM> findAll() {
        return this.repository.findAll();
    }

    @Override
    public void save(BM item) {
        this.repository.save(item);
    }

    @Override
    public void delete(BM item) {
        if (item != null && item.getId() != null) {
            this.repository.deleteById(item.getId());
        }
    }

    public void deleteById(String id) {
        this.repository.deleteById(id);
    }

    @Override
    public Optional<BM> findById(String id) {
        return this.repository.findById(id);
    }

    public RI getRepository() {
        return this.repository;
    }

}