package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.BaseModel;

import java.util.*;

public abstract class BaseRepository<T extends BaseModel> implements RepositoryInterface<T>  {

    protected List<T> items;

    public BaseRepository() {
        this.items = new ArrayList<>();
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(this.items);
    }

    @Override
    public Optional<T> findById(String id) {
        return this.items.stream().filter(item -> item.getId().equals(id)).findFirst();
    }
    public void save(T item) {
        if (item.getId() == null || item.getId().isBlank())
        {
            item.setId(UUID.randomUUID().toString());
        }

        this.findById(item.getId()).ifPresent(this.items::remove);

        this.items.add(item);

        this.saveItems();
    }

    @Override
    public void deleteById(String id) {
        this.items.removeIf(item -> item.getId().equals(id));
        this.saveItems();
    }

    @Override
    public void delete(T entity){
        this.items.removeIf(item -> item.getId().equals(entity.getId()));
        this.saveItems();
    }

    protected abstract void loadItems();

    protected abstract void saveItems();
}