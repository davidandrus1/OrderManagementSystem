package com.example.OrderManagementSystem.repository.documents;
import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.model.OrderLine;

import com.example.OrderManagementSystem.repository.BaseRepository;
import com.example.OrderManagementSystem.repository.InFileRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderLineRepository extends InFileRepository<OrderLine> {

    public OrderLineRepository() {
        super("src/main/resources/data/order-lines.json", OrderLine.class);
    }
}
