package com.example.OrderManagementSystem.repository;

import java.util.*;

public interface RepositoryInterface<T> {
    void save(T entity);
    List<T> findAll();
    Optional<T> findById(String id);
    boolean delete(T entity);
}