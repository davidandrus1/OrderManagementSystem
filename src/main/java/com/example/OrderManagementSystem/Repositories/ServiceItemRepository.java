package com.example.OrderManagementSystem.Repositories;

import com.example.OrderManagementSystem.Models.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import java.util.List;

@Repository
public interface ServiceItemRepository extends JpaRepository<ServiceItem, String> {

    List<ServiceItem> findByNameContainingIgnoreCase(String name, Sort sort);

    List<ServiceItem> findByStatus(String status, Sort sort);

    List<ServiceItem> findByNameContainingIgnoreCaseAndStatus(
            String name,
            String status,
            Sort sort
    );
}