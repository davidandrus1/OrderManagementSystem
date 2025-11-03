package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.UnitOfMeasure;
import com.example.OrderManagementSystem.service.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/units")
public class UnitOfMeasureController {

    private final UnitOfMeasureService unitService;

    public UnitOfMeasureController(UnitOfMeasureService unitService) {
        this.unitService = unitService;
    }
    @GetMapping
    public String viewAllUnits(Model model) {
        model.addAttribute("units", unitService.getAll());
        return "units/list"; // -> templates/units/list.html
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        UnitOfMeasure unit = new UnitOfMeasure(null, "","");
        model.addAttribute("unit", unit);
        return "units/create";
    }

    @PostMapping
    public String createUnit(@ModelAttribute UnitOfMeasure unit) {
        unitService.save(unit);
        return "redirect:/units";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        UnitOfMeasure unit = unitService.getById(id);
        model.addAttribute("unit", unit);
        return "units/edit";
    }

    @PostMapping("/update")
    public String updateUnit(@ModelAttribute UnitOfMeasure unit) {
        unitService.save(unit);
        return "redirect:/units";
    }

    @GetMapping("/delete/{id}")
    public String deleteUnit(@PathVariable String id) {
        unitService.delete(id);
        return "redirect:/units";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleValidationError(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("units", unitService.getAll());
        return "units/list";
    }
}
