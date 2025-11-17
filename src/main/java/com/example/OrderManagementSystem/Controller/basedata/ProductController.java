package com.example.OrderManagementSystem.controller.basedata;

import com.example.OrderManagementSystem.model.ProductItem;
import com.example.OrderManagementSystem.service.basedata.ProductItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductItemService service;

    public ProductController(ProductItemService service) {
        this.service = service;
    }

    @GetMapping
    public String viewAllProducts(Model model) {
        model.addAttribute("items", service.findAll());
        return "products/list";
    }

    @GetMapping("/new")
    public String showCreatePage(Model model) {
        model.addAttribute("item", new ProductItem());
        return "products/create";
    }

    @PostMapping({"/create", "/edit"})
    public String create(@ModelAttribute ProductItem product) {
        service.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    public String confirmDelete(@PathVariable String id, Model model) {
        service.findById(id).ifPresent(order -> model.addAttribute("item", order));
        return "products/delete";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        this.service.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String showEditPage(@PathVariable String id, Model model) {
        this.service.findById(id).ifPresent(item -> model.addAttribute("item", item)
        );
        return "products/edit";
    }
}