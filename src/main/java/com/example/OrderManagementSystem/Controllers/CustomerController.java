package com.example.OrderManagementSystem.Controllers;

import com.example.OrderManagementSystem.Models.Customer;
import com.example.OrderManagementSystem.Services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController extends BaseEntityController<Customer, CustomerService> {

    public CustomerController(CustomerService service) {
        super(service);
    }

    @Override
    protected String getListViewName() {
        return "customers";
    }

    @Override
    protected String getFormViewName() {
        return "customers-form";
    }

    @Override
    protected String getEntityName() {
        return "Customer";
    }

    @Override
    protected String getBaseUrl() {
        return "customers";
    }

    @Override
    protected Customer createNewEntity() {
        return new Customer();
    }




}
