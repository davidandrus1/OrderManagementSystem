package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.service.OrderService;
import com.example.OrderManagementSystem.service.ContractService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ContractService contractService;

    public OrderController(OrderService orderService, ContractService contractService) {
        this.orderService = orderService;
        this.contractService = contractService;
    }

    @GetMapping
    public String viewAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAll());
        return "orders/list"; // -> templates/orders/list.html
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        Order order = new Order(null, "", 0.0, null);
        model.addAttribute("order", order);
        model.addAttribute("contracts", contractService.getAll()); // pentru selectarea contractului
        return "orders/create";
    }

    @PostMapping
    public String createOrder(@ModelAttribute Order order, @RequestParam Long contractId) {
        order.setContractId(contractId);
        orderService.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Order order = orderService.getById(id);
        model.addAttribute("order", order);
        model.addAttribute("contracts", contractService.getAll());
        return "orders/edit";
    }

    @PostMapping("/update")
    public String updateOrder(@ModelAttribute Order order, @RequestParam Long contractId) {
        order.setContractId(contractId);
        orderService.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/details/{id}")
    public String viewOrderDetails(@PathVariable String id, Model model) {
        Order order = orderService.getById(id);
        model.addAttribute("order", order);
        return "orders/details";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable String id) {
        orderService.delete(id);
        return "redirect:/orders";
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleValidationError(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("orders", orderService.getAll());
        return "orders/list";
    }
}
