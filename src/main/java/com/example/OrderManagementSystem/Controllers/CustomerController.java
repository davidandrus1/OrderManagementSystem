package com.example.OrderManagementSystem.Controllers;

import com.example.OrderManagementSystem.Models.Contract;
import com.example.OrderManagementSystem.Models.Customer;
import com.example.OrderManagementSystem.Models.Order;
import com.example.OrderManagementSystem.Services.ContractService;
import com.example.OrderManagementSystem.Services.CustomerService;
import com.example.OrderManagementSystem.Services.OrderService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController extends BaseEntityController<Customer, CustomerService> {

    private final OrderService orderService;
    private final ContractService contractService;

    public CustomerController(CustomerService service, OrderService orderService, ContractService contractService) {
        super(service);
        this.orderService = orderService;
        this.contractService = contractService;
    }

    @Override
    protected String getListViewName() {
        return "customers";
    }

    @Override
    protected String getFormViewName() {
        return "customers-form";
    }

    @Override
    protected String getEntityName() {
        return "Customer";
    }

    @Override
    protected String getBaseUrl() {
        return "customers";
    }

    @Override
    protected Customer createNewEntity() {
        return new Customer();
    }

    // ✅ NOU - Override show() pentru filtrare
    @Override
    @GetMapping
    public String show(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String nameFilter,
            @RequestParam(required = false) String currencyFilter,
            Model model) {

        // 🔹 Construim sortarea
        Sort sort = Sort.unsorted();
        if (sortBy != null && !sortBy.isBlank()) {
            sort = "desc".equalsIgnoreCase(direction)
                    ? Sort.by(sortBy).descending()
                    : Sort.by(sortBy).ascending();
        }

        // 🔹 Filtrare + Sortare
        List<Customer> items;

        if (nameFilter != null && !nameFilter.isBlank() &&
                currencyFilter != null && !currencyFilter.isBlank()) {
            // Ambele filtre
            items = service.findByNameAndCurrency(nameFilter.trim(), currencyFilter.trim(), sort);
        } else if (nameFilter != null && !nameFilter.isBlank()) {
            // Doar Name
            items = service.findByName(nameFilter.trim(), sort);
        } else if (currencyFilter != null && !currencyFilter.isBlank()) {
            // Doar Currency
            items = service.findByCurrency(currencyFilter.trim(), sort);
        } else {
            // Fără filtre
            items = service.findAll(sort);
        }

        // 🔹 Model
        model.addAttribute("items", items);
        model.addAttribute("url", getBaseUrl());
        model.addAttribute("currentSort", sortBy);
        model.addAttribute("currentDirection", direction != null ? direction : "asc");
        model.addAttribute("nameFilter", nameFilter);
        model.addAttribute("currencyFilter", currencyFilter);

        return getListViewName();
    }

    @GetMapping("/{customerId}/orders")
    public String viewCustomerOrders(@PathVariable String customerId, Model model) {
        Customer customer = service.findById(customerId);
        if (customer == null) {
            return "redirect:/customers";
        }

        List<Order> orders = orderService.findByCustomerId(customerId);

        model.addAttribute("customer", customer);
        model.addAttribute("orders", orders);

        return "customer-orders";
    }

    @GetMapping("/{customerId}/contracts")
    public String viewCustomerContracts(@PathVariable String customerId, Model model) {
        Customer customer = service.findById(customerId);
        if (customer == null) {
            return "redirect:/customers";
        }

        List<Contract> contracts = contractService.findByCustomerId(customerId);

        model.addAttribute("customer", customer);
        model.addAttribute("contracts", contracts);

        return "customer-contracts";
    }
}