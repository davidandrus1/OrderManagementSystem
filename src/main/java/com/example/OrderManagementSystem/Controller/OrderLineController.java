package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.OrderLine;
import com.example.OrderManagementSystem.service.OrderLineService;
import com.example.OrderManagementSystem.service.SellableItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order-lines")
public class OrderLineController {

    private final OrderLineService orderLineService;
    private final SellableItemService sellableItemService;

    public OrderLineController(OrderLineService orderLineService, SellableItemService sellableItemService) {
        this.orderLineService = orderLineService;
        this.sellableItemService = sellableItemService;
    }

    @GetMapping
    public String viewAllOrderLines(Model model) {
        model.addAttribute("orderLines", orderLineService.getAll());
        return "order-lines/list";
    }


    @GetMapping("/new")
    public String showCreateForm(Model model) {
        OrderLine orderLine = new OrderLine(null, null, null, 0);
        model.addAttribute("orderLine", orderLine);
        model.addAttribute("items", sellableItemService.getAll());
        return "order-lines/create";
    }

    @PostMapping
    public String createOrderLine(@ModelAttribute OrderLine orderLine,
                                  @RequestParam long itemId) {
        orderLine.setItem(sellableItemService.getById(itemId));
        orderLineService.save(orderLine);
        return "redirect:/order-lines";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        OrderLine orderLine = orderLineService.getById(id);
        model.addAttribute("orderLine", orderLine);
        model.addAttribute("items", sellableItemService.getAll());
        return "order-lines/edit";
    }

    @PostMapping("/update")
    public String updateOrderLine(@ModelAttribute OrderLine orderLine,
                                  @RequestParam long itemId) {
        orderLine.setItem(sellableItemService.getById(itemId));
        orderLineService.save(orderLine);
        return "redirect:/order-lines";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrderLine(@PathVariable String id) {
        orderLineService.delete(id);
        return "redirect:/order-lines";
    }
}
