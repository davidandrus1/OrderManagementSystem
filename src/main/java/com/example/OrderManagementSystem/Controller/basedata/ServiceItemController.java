package com.example.OrderManagementSystem.controller.basedata;

import com.example.OrderManagementSystem.model.ServiceItem;
import com.example.OrderManagementSystem.service.basedata.ServiceItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/service-items")
public class ServiceItemController {

    private final ServiceItemService service;

    public ServiceItemController(ServiceItemService service) {
        this.service = service;
    }

    @GetMapping
    public String viewAllServiceItems(Model model) {
        model.addAttribute("items", service.findAll());
        return "service-items/list";
    }

    @GetMapping("/new")
    public String showCreatePage(Model model) {
      model.addAttribute("service-items", new ServiceItem());
        return "service-items/create";
    }

    @PostMapping({"/create", "/edit"})
    public String create(@ModelAttribute ServiceItem serviceItem) {
        service.save(serviceItem);
        return "redirect:/service-items";
    }

    @GetMapping("/{id}/delete")
    public String showDeletePage(@PathVariable String id, Model model) {
        service.findById(id).ifPresent(item -> model.addAttribute("item", item));
        return "service-items/delete";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.deleteById(id);
        return "redirect:/service-items";
    }

    @GetMapping("/{id}/edit")
    public String showEditPage(@PathVariable String id, Model model) {
        this.service.findById(id).ifPresent(item -> model.addAttribute("item", item)
        );
        return "service-items/edit";
    }
}
