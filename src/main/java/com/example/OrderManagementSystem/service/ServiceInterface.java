package com.example.OrderManagementSystem.service;

import java.util.*;

public interface ServiceInterface<T> {

    List<T> findAll();
    void save(T item);
    void delete(T item);
    Optional<T> findById(String id);

}