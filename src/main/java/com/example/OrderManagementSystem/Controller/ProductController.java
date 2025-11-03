package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.Product;
import com.example.OrderManagementSystem.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String viewAllProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        return "products/list"; // -> src/main/resources/templates/products/list.html
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        Product product = new Product(null, "", 0.0);
        model.addAttribute("product", product);
        return "products/create"; // -> src/main/resources/templates/products/create.html
    }

    @PostMapping
    public String createProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "products/edit"; // -> src/main/resources/templates/products/edit.html
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        productService.delete(id);
        return "redirect:/products";
    }
}
