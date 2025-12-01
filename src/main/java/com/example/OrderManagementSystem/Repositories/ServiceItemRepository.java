package com.example.OrderManagementSystem.Repositories;

import com.example.OrderManagementSystem.Models.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceItemRepository extends JpaRepository<ServiceItem, String> {
}
