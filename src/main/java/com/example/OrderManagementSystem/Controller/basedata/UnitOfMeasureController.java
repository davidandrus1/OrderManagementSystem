package com.example.OrderManagementSystem.controller.basedata;

import com.example.OrderManagementSystem.model.UnitOfMeasure;
import com.example.OrderManagementSystem.service.basedata.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/units")
public class UnitOfMeasureController {

    private final UnitOfMeasureService service;

    public UnitOfMeasureController(UnitOfMeasureService service) {
        this.service = service;
    }

    @GetMapping
    public String viewAllUnits(Model model) {
        model.addAttribute("units", service.findAll());
        return "units/list";
    }

    @GetMapping("/new")
    public String showCreatePage(Model model) {
        model.addAttribute("unit", new UnitOfMeasure());
        return "units/create";
    }

    @PostMapping({"/create", "edit"})
    public String create(@ModelAttribute UnitOfMeasure unitOfMeasure) {
        service.save(unitOfMeasure);
        return "redirect:/units";
    }

    @GetMapping("/{id}/delete")
    public String confirmDelete(@PathVariable String id, Model model) {
        service.findById(id).ifPresent(item -> model.addAttribute("unit", item));
        return "units/delete";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.deleteById(id);
        return "redirect:/units";
    }

    @GetMapping("/{id}/edit")
    public String showEditPage(@PathVariable String id, Model model) {
        this.service.findById(id).ifPresent(item -> model.addAttribute("unit", item)
        );
        return "units/edit";
    }
}