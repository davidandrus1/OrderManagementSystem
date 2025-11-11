package com.example.OrderManagementSystem.repository;
import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.model.Customer;
import java.util.*;
import org.springframework.stereotype.Repository;


    @Repository
    public class CustomerRepository extends BaseRepository<Customer> {

        @Override
        protected String getEntityId(Customer entity) {
            return entity.getId();
        }
    }
