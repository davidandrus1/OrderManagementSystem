package com.example.OrderManagementSystem.controller.basedata;

import com.example.OrderManagementSystem.model.ContractType;
import com.example.OrderManagementSystem.service.basedata.ContractTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/contract-types")
public class ContractTypeController {

    private final ContractTypeService service;

    public ContractTypeController(ContractTypeService service) {
        this.service = service;
    }

    @GetMapping
    public String viewAllContractTypes(Model model) {
        model.addAttribute("items", service.findAll());
        return "contract-types/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("item", new ContractType());
        return "contract-types/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute ContractType contractType) {
        contractType.setId(UUID.randomUUID().toString());
        service.save(contractType);
        return "redirect:/contract-types";
    }

    @GetMapping("/{id}/delete")
    public String confirmDelete(@PathVariable String id, Model model) {
        service.findById(id).ifPresent(item -> model.addAttribute("item", item));
        return "contract-types/delete";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.deleteById(id);
        return "redirect:/contract-types";
    }
}
