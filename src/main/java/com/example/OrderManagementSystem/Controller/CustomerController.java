package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.Customer;
import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String viewAllCustomers(Model model) {
        model.addAttribute("customers", customerService.getAll());
        return "customers/list"; // -> templates/customers/list.html
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        Customer customer = new Customer(null, "", "", null, null);
        model.addAttribute("customer", customer);
        return "customers/create";
    }

    @PostMapping
    public String createCustomer(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Customer customer = customerService.getById(id);
        model.addAttribute("customer", customer);
        return "customers/edit";
    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable String id) {
        customerService.delete(id);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/total")
    public String viewCustomerTotal(@PathVariable String id, Model model) {
        double total = customerService.calculateTotalForCustomer(id);
        model.addAttribute("customer", customerService.getById(id));
        model.addAttribute("totalAmount", total);
        return "customers/total";
    }

    @PostMapping("/{id}/orders")
    public String addOrderToCustomer(@PathVariable String id, @ModelAttribute Order order) {
        customerService.addOrderToCustomer(id, order);
        return "redirect:/customers";
    }

    @PostMapping("/{id}/contracts")
    public String addContractToCustomer(@PathVariable String id, @ModelAttribute Contract contract) {
        customerService.addContractToCustomer(id, contract);
        return "redirect:/customers";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleValidationError(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("customers", customerService.getAll());
        return "customers/list";
    }
}
