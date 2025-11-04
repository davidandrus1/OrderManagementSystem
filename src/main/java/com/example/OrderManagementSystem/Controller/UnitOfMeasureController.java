package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.UnitOfMeasure;
import com.example.OrderManagementSystem.service.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/units")
public class UnitOfMeasureController {

    private final UnitOfMeasureService service;

    public UnitOfMeasureController(UnitOfMeasureService service) {
        this.service = service;

         this.service.save(new UnitOfMeasure("1","kg", "Kilogram"));
         this.service.save(new UnitOfMeasure("2","L", "Liter"));
    }

    @GetMapping
    public String viewAllUnits(Model model) {
        model.addAttribute("items", service.getAll());
        return "units/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("item", new UnitOfMeasure());
        return "units/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute UnitOfMeasure unitOfMeasure) {
        service.save(unitOfMeasure);
        return "redirect:/units";
    }

    @GetMapping("/{id}/delete")
    public String confirmDelete(@PathVariable String id, Model model) {
        service.getById(id).ifPresent(item -> model.addAttribute("item", item));
        return "units/delete";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/units";
    }
}