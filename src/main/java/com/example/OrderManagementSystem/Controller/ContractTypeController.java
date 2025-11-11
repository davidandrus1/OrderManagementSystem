package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.ContractType;
import com.example.OrderManagementSystem.service.ContractTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contract-types")
public class ContractTypeController {

    private final ContractTypeService service;

    public ContractTypeController() {
        this.service = new ContractTypeService();

        this.service.save(new ContractType("Prestari Servicii", "Seller"));
        this.service.save(new ContractType("Vanzare En-gros", "Customer"));
        this.service.save(new ContractType("Contract Mentenanta", "Supplier"));
    }

    @GetMapping
    public String viewAllContractTypes(Model model) {
        model.addAttribute("items", service.getAll());
        return "contract-types/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("item", new ContractType());
        return "contract-types/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute ContractType contractType) {
        service.save(contractType);
        return "redirect:/contract-types";
    }

    @GetMapping("/{id}/delete")
    public String confirmDelete(@PathVariable String id, Model model) {
        service.getById(id).ifPresent(item -> model.addAttribute("item", item));
        return "contract-types/delete";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/contract-types";
    }
}
