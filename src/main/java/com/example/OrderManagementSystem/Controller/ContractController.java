package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.service.ContractService;
import com.example.OrderManagementSystem.service.ContractTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService contractService;
    private final ContractTypeService contractTypeService;

    public ContractController(ContractService contractService,
                              ContractTypeService contractTypeService) {
        this.contractService = contractService;
        this.contractTypeService = contractTypeService;
    }

    @GetMapping
    public String viewAllContracts(Model model) {
        model.addAttribute("contracts", contractService.getAll());
        return "contracts/list"; // -> templates/contracts/list.html
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        Contract contract = new Contract(null, "", null, "");
        model.addAttribute("contract", contract);
        model.addAttribute("contractTypes", contractTypeService.getAll()); // pentru dropdown
        return "contracts/create";
    }

    @PostMapping
    public String createContract(@ModelAttribute Contract contract,
                                 @RequestParam Long contractTypeId) {
        contract.setContractTypeId(contractTypeId);
        contractService.save(contract);
        return "redirect:/contracts";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Contract contract = contractService.getById(id);
        model.addAttribute("contract", contract);
        model.addAttribute("contractTypes", contractTypeService.getAll());
        return "contracts/edit";
    }

    @PostMapping("/update")
    public String updateContract(@ModelAttribute Contract contract,
                                 @RequestParam Long contractTypeId) {
        contract.setContractTypeId(contractTypeId);
        contractService.save(contract);
        return "redirect:/contracts";
    }

    @GetMapping("/delete/{id}")
    public String deleteContract(@PathVariable String id) {
        contractService.delete(id);
        return "redirect:/contracts";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleValidationError(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("contracts", contractService.getAll());
        return "contracts/list";
    }
}
