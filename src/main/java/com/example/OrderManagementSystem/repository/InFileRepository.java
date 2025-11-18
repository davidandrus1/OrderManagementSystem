package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.BaseModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class InFileRepository<T extends BaseModel> implements RepositoryInterface<T> {

    protected List<T> items;
    protected final String filePath;
    protected final File file;
    protected final ObjectMapper objectMapper;
    private final Class<T> modelClass;

    public InFileRepository(String filePath, Class<T> modelClass) {
        this.items = new ArrayList<>();
        this.filePath = filePath;
        this.file = new File(filePath);
        this.objectMapper = new ObjectMapper();
        this.modelClass = modelClass;

        this.loadItems();
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(this.items);
    }

    @Override
    public Optional<T> findById(String id) {
        return this.items.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    @Override
    public void save(T item) {
        if (item.getId() == null || item.getId().isBlank()) {
            item.setId(UUID.randomUUID().toString());
        }

        this.findById(item.getId()).ifPresent(existing -> this.items.remove(existing));

        this.items.add(item);
        this.saveItems();
    }

    @Override
    public void deleteById(String id) {
        this.items.removeIf(item -> item.getId().equals(id));
        this.saveItems();
    }

    @Override
    public void delete(T entity) {
        this.items.removeIf(item -> item.getId().equals(entity.getId()));
        this.saveItems();
    }

    private void loadItems() {
        try {
            if (!file.exists()) {
                file.createNewFile();
                this.items = new ArrayList<>();
                return;
            }

            this.items = this.objectMapper.readValue(
                    this.file,
                    this.objectMapper.getTypeFactory().constructCollectionType(List.class, this.modelClass)
            );

        } catch (IOException e) {
            e.printStackTrace();
            this.items = new ArrayList<>();
        }
    }

    private void saveItems() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, this.items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}