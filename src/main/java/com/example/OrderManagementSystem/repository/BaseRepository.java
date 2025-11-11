package com.example.OrderManagementSystem.repository;

import java.util.*;

public abstract class BaseRepository<T> implements RepositoryInterface<T>{

    protected final List<T> list;

    public BaseRepository() {
        this.list = new java.util.ArrayList<>();
    }

    @Override
    public void save(T entity) {
        this.list.add(entity);
    }

    @Override
    public List<T> findAll() {
        return list;
    }

    @Override
    public boolean delete(T entity) {
        return this.list.remove(entity);
    }

    protected abstract String getEntityId(T entity);

    @Override
    public Optional<T> findById(String id) {
        return this.list.stream()
                .filter(e -> getEntityId(e).equals(id))
                .findFirst();
    }
}
