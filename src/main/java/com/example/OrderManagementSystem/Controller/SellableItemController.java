package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.Product;
import com.example.OrderManagementSystem.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sellable-items")
public class SellableItemController {

    private final ProductService productService;

    public SellableItemController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String viewAllProducts(Model model) {
        model.addAttribute("items", productService.getAll());
        return "sellable-items/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        Product product = new Product(null, "", 0.0);
        model.addAttribute("item", product);
        return "sellable-items/create";
    }

    @PostMapping
    public String createProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/sellable-items";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("item", product);
        return "sellable-items/edit";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/sellable-items";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        productService.delete(id);
        return "redirect:/sellable-items";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleValidationError(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("items", productService.getAll());
        return "sellable-items/list";
    }
}
