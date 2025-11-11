package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.Customer;
import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
        List<Order> orders_c1 = List.of(
                new Order("1", "ORD-001", 1250.50, 1L),
                new Order("2", "ORD-002", 980.75, 2L)
        );

        List<Contract> contracts_c1 = List.of(
                new Contract("C-001", 1, "Active"),
                new Contract("C-002", 2, "Inactive")
        );

        this.service.save(new Customer("1", "Kaufland", "RON", orders_c1, contracts_c1));
    }

    @GetMapping
    public String viewAllCustomrs(Model model) {
        model.addAttribute("items", service.getAll());
        return "customers/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("item", new Customer());
        return "customers/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Customer customer) {
        service.save(customer);
        return "redirect:/customers";
    }


    @GetMapping("/{id}/delete")
    public String confirmDelete(@PathVariable String id, Model model) {
        this.service.getById(id).ifPresent(item -> model.addAttribute("item", item)
        );
        return "customers/delete";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        this.service.delete(id);
        return "redirect:/customers";
    }
}