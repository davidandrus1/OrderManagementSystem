package com.example.OrderManagementSystem.Controllers;

import com.example.OrderManagementSystem.Models.Customer;
import com.example.OrderManagementSystem.Models.Order;
import com.example.OrderManagementSystem.Services.CustomerService;
import com.example.OrderManagementSystem.Services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController extends BaseEntityController<Customer, CustomerService> {

    private final OrderService orderService;

    public CustomerController(CustomerService service, OrderService orderService) {
        super(service);
        this.orderService = orderService;
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

    @GetMapping("/{customerId}/orders")
    public String viewCustomerOrders(@PathVariable String customerId, Model model) {
        Customer customer = service.findById(customerId);
        if (customer == null) {
            return "redirect:/customers";
        }

        List<Order> orders = orderService.findByCustomerId(customerId);

        model.addAttribute("customer", customer);
        model.addAttribute("orders", orders);

        return "customer-orders";  // numele template-ului HTML
    }
}