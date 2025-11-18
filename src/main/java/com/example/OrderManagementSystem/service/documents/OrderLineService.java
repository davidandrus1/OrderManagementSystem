package com.example.OrderManagementSystem.service.documents;

import com.example.OrderManagementSystem.model.*;
import com.example.OrderManagementSystem.repository.documents.OrderLineRepository;
import com.example.OrderManagementSystem.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderLineService extends BaseService<OrderLine, OrderLineRepository> {

    public OrderLineService(OrderLineRepository repository) {
        super(repository);
    }

    public OrderLine createOrderLine(String name, double quantity, UnitOfMeasure unit, String type) {
        SellableItem item;

        if ("product".equalsIgnoreCase(type)) {
            ProductItem p = new ProductItem();
            p.setId(UUID.randomUUID().toString());
            p.setName(name);
            p.setValue(100);
            item = p;
        }
        else if ("service".equalsIgnoreCase(type)) {
            ServiceItem s = new ServiceItem();
            s.setId(UUID.randomUUID().toString());
            s.setName(name);
            s.setStatus(ServiceItem.Status.ACTIVE);
            item = s;
        }
        else {
            throw new IllegalArgumentException("Unknown type: " + type);
        }

        OrderLine line = new OrderLine();
        line.setId(UUID.randomUUID().toString());
        line.setItem(item);
        line.setQuantity(quantity);
        line.setUnit(unit);

        this.save(line);

        return line;
    }
}