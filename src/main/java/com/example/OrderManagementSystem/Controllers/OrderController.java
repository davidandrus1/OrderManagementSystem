package com.example.OrderManagementSystem.Controllers;

import com.example.OrderManagementSystem.Models.Order;
import com.example.OrderManagementSystem.Services.ContractService;
import com.example.OrderManagementSystem.Services.CustomerService;
import com.example.OrderManagementSystem.Services.OrderService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

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
    @GetMapping
    public String show(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            Model model) {

        List<Order> items;

        if (sortBy != null && !sortBy.isEmpty()) {
            if ("lines".equalsIgnoreCase(sortBy)) {
                items = service.findAll();
                Comparator<Order> comparator = Comparator.comparingInt(o -> o.getOrderLines().size());

                if ("desc".equalsIgnoreCase(direction)) {
                    comparator = comparator.reversed();
                }

                items.sort(comparator);
            } else if ("customer.name".equalsIgnoreCase(sortBy)) {
                items = service.findAll();
                Comparator<Order> comparator = Comparator.comparing(
                        o -> o.getCustomer() != null ? o.getCustomer().getName() : "",
                        String.CASE_INSENSITIVE_ORDER
                );

                if ("desc".equalsIgnoreCase(direction)) {
                    comparator = comparator.reversed();
                }

                items.sort(comparator);
            } else {
                Sort sort = "desc".equalsIgnoreCase(direction)
                        ? Sort.by(sortBy).descending()
                        : Sort.by(sortBy).ascending();
                items = service.findAll(sort);
            }
        } else {
            items = service.findAll();
        }

        model.addAttribute("items", items);
        model.addAttribute("url", getBaseUrl());
        model.addAttribute("currentSort", sortBy);
        model.addAttribute("currentDirection", direction != null ? direction : "asc");

        return getListViewName();
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
        model.addAttribute("lines", order.getOrderLines());
        model.addAttribute("url", "order-lines");
        return "order-lines";
    }
}