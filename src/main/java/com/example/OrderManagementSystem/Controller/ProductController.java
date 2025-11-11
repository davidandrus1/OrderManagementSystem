package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.Product;
import com.example.OrderManagementSystem.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;

         this.service.save(new Product("P-1", "Laptop", 3500.00));
    }

    @GetMapping
    public String viewAllProducts(Model model) {
        model.addAttribute("items", service.getAll());
        return "products/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("item", new Product());
        return "products/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Product product) {
        service.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    public String confirmDelete(@PathVariable String id, Model model) {
        service.getById(id).ifPresent(order -> model.addAttribute("item", order));
        return "products/delete";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        this.service.delete(id);
        return "redirect:/products";
    }
}