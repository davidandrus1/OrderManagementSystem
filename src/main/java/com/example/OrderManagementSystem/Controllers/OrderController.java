package com.example.OrderManagementSystem.Controllers;

import com.example.OrderManagementSystem.Models.Contract;
import com.example.OrderManagementSystem.Models.Customer;
import com.example.OrderManagementSystem.Models.Order;
import com.example.OrderManagementSystem.Services.OrderService;
import com.example.OrderManagementSystem.Services.CustomerService;
import com.example.OrderManagementSystem.Services.ContractService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController extends BaseEntityController<Order, OrderService> {

    private final CustomerService customerService;
    private final ContractService contractService;

    public OrderController(OrderService service, CustomerService customerService, ContractService contractService) {
        super(service);
        this.customerService = customerService;
        this.contractService = contractService;
    }

    @Override
    protected String getListViewName() {
        return "orders";
    }

    @Override
    protected String getFormViewName() {
        return "orders-form";
    }

    @Override
    protected String getEntityName() {
        return "Order";
    }

    @Override
    protected String getBaseUrl() {
        return "orders";
    }

    @Override
    protected Order createNewEntity() {
        return new Order();
    }

    @Override
    @GetMapping({"/{action}", "/{action}/{id}"})
    public String showForm(@PathVariable String action, @PathVariable(required = false) String id, Model model) {
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("contracts", contractService.findAll());
        return super.showForm(action, id, model);
    }

    @GetMapping("/view/{id}")
    public String viewOrder(@PathVariable String id, Model model) {
        Order order = service.findById(id);
        if (order == null) {
            return "redirect:/orders";
        }
        model.addAttribute("order", order);
        return "order-lines";
    }

    @PostMapping("/save-order")
    public String save(
            @RequestParam(required = false) String id,
            @RequestParam String name,  // required = true (implicit)
            @RequestParam String customerId,  // required = true
            @RequestParam(required = false) String contractId,
            @RequestParam String action,
            Model model) {

        if ("delete".equals(action)) {
            service.deleteById(id);
            return "redirect:/orders";
        }

        Order order;
        if (id != null && !id.isEmpty()) {
            order = service.findById(id);
            if (order == null) {
                return "redirect:/orders";
            }
        } else {
            order = new Order();
        }

        // Validare manuală
        boolean hasErrors = false;

        if (name == null || name.trim().isEmpty()) {
            model.addAttribute("nameError", "Order name is required");
            hasErrors = true;
        } else if (name.length() < 2 || name.length() > 128) {
            model.addAttribute("nameError", "Order name must be between 2 and 128 characters");
            hasErrors = true;
        }

        if (customerId == null || customerId.trim().isEmpty()) {
            model.addAttribute("customerError", "Customer is required");
            hasErrors = true;
        }

        Customer customer = null;
        if (customerId != null && !customerId.trim().isEmpty()) {
            customer = customerService.findById(customerId);
            if (customer == null) {
                model.addAttribute("customerError", "Invalid customer selected");
                hasErrors = true;
            }
        }

        if (hasErrors) {
            // Setează datele pe order pentru a le afișa în formular
            order.setName(name);
            order.setCustomer(customer);

            if (contractId != null && !contractId.isEmpty()) {
                Contract contract = contractService.findById(contractId);
                order.setContract(contract);
            }

            // IMPORTANT: Adaugă toate atributele necesare pentru formular
            model.addAttribute("item", order);  // ← ASTA LIPSEA!
            model.addAttribute("action", id != null && !id.isEmpty() ? "edit" : "create");
            model.addAttribute("title", id != null ? "Edit Order" : "Add New Order");
            model.addAttribute("caption", id != null ? "Save" : "Create");
            model.addAttribute("url", getBaseUrl());
            model.addAttribute("customers", customerService.findAll());
            model.addAttribute("contracts", contractService.findAll());

            return getFormViewName();
        }

        // Dacă nu sunt erori, salvează
        Contract contract = null;
        if (contractId != null && !contractId.isEmpty()) {
            contract = contractService.findById(contractId);
        }

        order.setName(name.trim());
        order.setCustomer(customer);
        order.setContract(contract);

        service.save(order);
        return "redirect:/orders";
    }
}