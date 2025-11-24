package com.example.OrderManagementSystem.controller.documents;

import com.example.OrderManagementSystem.model.Customer;
import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.service.basedata.CustomerService;
import com.example.OrderManagementSystem.service.documents.OrderService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;
    private final CustomerService customerService;

    public OrderController(OrderService service, CustomerService customerService) {
        this.service = service;
        this.customerService = customerService;
    }

    @GetMapping
    public String viewAllOrders(Model model) {
        List<Order> orders = service.findAll();
        model.addAttribute("items", orders);
        return "orders/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("item", new Order());
        model.addAttribute("customers", customerService.findAll());
        return "orders/create";
    }

    @PostMapping("/create")
    public String create(@RequestParam("id") String id,
                         @RequestParam("name") String name,
                         @RequestParam("customer") String customerId) {

        Customer customer = customerService.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setId(id);
        order.setName(name);
        order.setCustomer(customer);

        service.save(order);
        return "redirect:/orders";
    }


    @GetMapping("/{id}/delete")
    public String confirmDelete(@PathVariable String id, Model model) {
        service.findById(id).ifPresent(order -> model.addAttribute("item", order));
        return "orders/delete";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.deleteById(id);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String showEditPage(@PathVariable String id, Model model) {
        service.findById(id).ifPresent(order -> model.addAttribute("item", order));
        model.addAttribute("customers", customerService.findAll());
        return "orders/edit";
    }

    @PostMapping("/edit")
    public String saveEdit(@ModelAttribute Order order) {
        service.save(order);
        return "redirect:/orders";
    }

}
