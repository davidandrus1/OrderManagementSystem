package com.example.OrderManagementSystem.Repositories;

import com.example.OrderManagementSystem.Models.UnitOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, String> {
}
