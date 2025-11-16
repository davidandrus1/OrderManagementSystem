package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.Customer;
import com.example.OrderManagementSystem.service.basedata.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public String viewAllCustomrs(Model model) {
        model.addAttribute("items", service.findAll());
        return "customers/list";
    }

    @GetMapping("/new")
    public String showCreatePage(Model model) {
        model.addAttribute("item", new Customer());
        return "customers/create";
    }

    @PostMapping({ "/create", "/edit" })
    public String create(@ModelAttribute Customer customer) {
        service.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/delete")
    public String showDeletePage(@PathVariable String id, Model model) {
        this.service.findById(id).ifPresent(item -> model.addAttribute("item", item)
        );
        return "customers/delete";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        this.service.deleteById(id);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/edit")
    public String showEditPage(@PathVariable String id, Model model) {
        this.service.findById(id).ifPresent(item -> model.addAttribute("item", item)
        );
        return "customers/edit";
    }

}