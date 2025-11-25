package com.example.OrderManagementSystem.repository.documents;
import com.example.OrderManagementSystem.model.Order;

import com.example.OrderManagementSystem.repository.InFileRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class OrderRepository extends InFileRepository<Order> {

    public OrderRepository() {
//        super("data/orders.json", Order.class);
        super("src/main/resources/data/orders.json", Order.class);
    }

    @Override
    public void loadItems(){
        try{
            this.items =  objectMapper.readValue(this.file, new TypeReference<List<Order>>() {});
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveItems() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, this.items);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save orders", e);
        }
    }
}

