package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.ContractType;
import com.example.OrderManagementSystem.service.ContractTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contract-types")
public class ContractTypeController {

    private final ContractTypeService contractTypeService;

    public ContractTypeController(ContractTypeService contractTypeService) {
        this.contractTypeService = contractTypeService;
    }

    @GetMapping
    public String viewAllContractTypes(Model model) {
        model.addAttribute("contractTypes", contractTypeService.getAll());
        return "contract-types/list"; // -> templates/contract-types/list.html
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        ContractType contractType = new ContractType(null, "", "");
        model.addAttribute("contractType", contractType);
        return "contract-types/create";
    }


    @PostMapping
    public String createContractType(@ModelAttribute ContractType contractType) {
        contractTypeService.save(contractType);
        return "redirect:/contract-types";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        ContractType contractType = contractTypeService.getById(id);
        model.addAttribute("contractType", contractType);
        return "contract-types/edit";
    }

    @PostMapping("/update")
    public String updateContractType(@ModelAttribute ContractType contractType) {
        contractTypeService.save(contractType);
        return "redirect:/contract-types";
    }

    @GetMapping("/delete/{id}")
    public String deleteContractType(@PathVariable String id) {
        contractTypeService.delete(id);
        return "redirect:/contract-types";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleValidationError(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("contractTypes", contractTypeService.getAll());
        return "contract-types/list";
    }
}

