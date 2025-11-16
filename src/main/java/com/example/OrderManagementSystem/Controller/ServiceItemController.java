package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.ServiceItem;
import com.example.OrderManagementSystem.service.basedata.ServiceItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/service-items")
public class ServiceItemController {
//
//    private final ServiceItemService service;
//
//    public ServiceItemController(ServiceItemService service) {
//        this.service = service;
//
//    }
//
//    @GetMapping
//    public String viewAllServiceItems(Model model) {
//        model.addAttribute("items", service.getAll());
//        return "service-items/list";
//    }
//
//    @GetMapping("/new")
//    public String showCreateForm(Model model) {
//      model.addAttribute("item", new ServiceItem(null, null, "Active")); // default status
//        return "service-items/create";
//    }
//
//    @PostMapping("/create")
//    public String create(@ModelAttribute ServiceItem serviceItem) {
//        service.save(serviceItem);
//        return "redirect:/service-items";
//    }
//
//    @GetMapping("/{id}/delete")
//    public String confirmDelete(@PathVariable String id, Model model) {
//        service.getById(id).ifPresent(item -> model.addAttribute("item", item));
//        return "service-items/delete";
//    }
//
//    @PostMapping("/{id}/delete")
//    public String delete(@PathVariable String id) {
//        service.delete(id);
//        return "redirect:/service-items";
//    }
}
