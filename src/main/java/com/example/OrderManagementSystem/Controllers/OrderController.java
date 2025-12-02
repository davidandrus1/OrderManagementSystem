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

    // Override showForm pentru a adăuga customers și contracts
    @Override
    @GetMapping({"/{action}", "/{action}/{id}"})
    public String showForm(@PathVariable String action, @PathVariable(required = false) String id, Model model) {
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("contracts", contractService.findAll());
        return super.showForm(action, id, model);
    }

    // Endpoint pentru a vedea detaliile unui order cu lines
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
            @RequestParam(required = false) String name,  // required = false
            @RequestParam(required = false) String customerId,  // required = false
            @RequestParam(required = false) String contractId,
            @RequestParam String action) {

        if ("delete".equals(action)) {
            service.deleteById(id);
        } else {
            Order order;
            if (id != null && !id.isEmpty()) {
                // Edit
                order = service.findById(id);
                if (order == null) {
                    return "redirect:/orders";
                }
            } else {
                // Create
                order = new Order();
            }

            Customer customer = customerService.findById(customerId);
            Contract contract = null;
            if (contractId != null && !contractId.isEmpty()) {
                contract = contractService.findById(contractId);
            }

            order.setName(name);
            order.setCustomer(customer);
            order.setContract(contract);

            service.save(order);
        }

        return "redirect:/orders";
    }
}