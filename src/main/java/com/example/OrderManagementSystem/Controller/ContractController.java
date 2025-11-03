package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.service.ContractService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller pentru gestionarea contractelor - versiune MVC (cu template-uri HTML)
 */
@Controller
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    /**
     * Afișează lista tuturor contractelor.
     */
    @GetMapping
    public String viewAllContracts(Model model) {
        model.addAttribute("contracts", contractService.getAll());
        return "contracts/list";  // View: templates/contracts/list.html
    }

    /**
     * Form pentru crearea unui contract nou.
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("contract", new Contract());
        return "contracts/create";  // View: templates/contracts/create.html
    }

    /**
     * Salvează un contract nou.
     */
    @PostMapping
    public String createContract(@ModelAttribute Contract contract) {
        contractService.save(contract);
        return "redirect:/contracts";
    }

    /**
     * Form pentru editarea unui contract existent.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Contract contract = contractService.getById(id);
        model.addAttribute("contract", contract);
        return "contracts/edit";  // View: templates/contracts/edit.html
    }

    /**
     * Actualizează un contract existent.
     */
    @PostMapping("/update")
    public String updateContract(@ModelAttribute Contract contract) {
        contractService.update(contract.getId(), contract);
        return "redirect:/contracts";
    }

    /**
     * Șterge un contract după ID.
     */
    @GetMapping("/delete/{id}")
    public String deleteContract(@PathVariable String id) {
        contractService.delete(id);
        return "redirect:/contracts";
    }
}
