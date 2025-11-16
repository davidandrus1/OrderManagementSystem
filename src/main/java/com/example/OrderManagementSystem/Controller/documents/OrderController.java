package com.example.OrderManagementSystem.controller.documents;

import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.service.documents.OrderService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
//
//    private final OrderService service;
//
//    public OrderController(OrderService service) {
//        this.service = service;
//    }
//
//    @GetMapping
//    public String viewAllOrders(Model model) {
//        List<Order> orders = service.getAll();
//        model.addAttribute("items", orders);
//        return "orders/list";
//    }
//
//    @GetMapping("/new")
//    public String showCreateForm(Model model) {
//        model.addAttribute("item", new Order());
//        return "orders/create";
//    }
//
//    @PostMapping("/create")
//    public String create(@ModelAttribute Order order) {
//        service.save(order);
//        return "redirect:/orders";
//    }
//
//    @GetMapping("/{id}/delete")
//    public String confirmDelete(@PathVariable String id, Model model) {
//        service.getById(id).ifPresent(order -> model.addAttribute("item", order));
//        return "orders/delete";
//    }
//
//    @PostMapping("/{id}/delete")
//    public String delete(@PathVariable String id) {
//        service.delete(id);
//        return "redirect:/orders";
//    }

}
