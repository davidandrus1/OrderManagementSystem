package com.example.OrderManagementSystem.Repositories;

import com.example.OrderManagementSystem.Models.UnitOfMeasure;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, String> {

    List<UnitOfMeasure> findByNameContainingIgnoreCase(String name, Sort sort);

    List<UnitOfMeasure> findBySymbolContainingIgnoreCase(String symbol, Sort sort);

    List<UnitOfMeasure> findByNameContainingIgnoreCaseAndSymbolContainingIgnoreCase(
            String name,
            String symbol,
            Sort sort
    );
}
