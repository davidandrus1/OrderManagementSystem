package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.ContractLine;
import com.example.OrderManagementSystem.service.ContractLineService;
import com.example.OrderManagementSystem.service.SellableItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contract-lines")
public class ContractLineController {

    private final ContractLineService contractLineService;
    private final SellableItemService sellableItemService;

    public ContractLineController(ContractLineService contractLineService, SellableItemService sellableItemService) {
        this.contractLineService = contractLineService;
        this.sellableItemService = sellableItemService;
    }

    @GetMapping
    public String viewAllContractLines(Model model) {
        model.addAttribute("contractLines", contractLineService.getAll());
        return "contract-lines/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        ContractLine contractLine = new ContractLine(null, null, null, 0);
        model.addAttribute("contractLine", contractLine);
        model.addAttribute("items", sellableItemService.getAll());
        return "contract-lines/create";
    }

    @PostMapping
    public String createContractLine(@ModelAttribute ContractLine contractLine,
                                     @RequestParam long itemId) {
        contractLine.setItem(sellableItemService.getById(itemId));
        contractLineService.save(contractLine);
        return "redirect:/contract-lines";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        ContractLine contractLine = contractLineService.getById(id);
        model.addAttribute("contractLine", contractLine);
        model.addAttribute("items", sellableItemService.getAll());
        return "contract-lines/edit";
    }

    @PostMapping("/update")
    public String updateContractLine(@ModelAttribute ContractLine contractLine,
                                     @RequestParam long itemId) {
        contractLine.setItem(sellableItemService.getById(itemId));
        contractLineService.save(contractLine);
        return "redirect:/contract-lines";
    }

    @GetMapping("/delete/{id}")
    public String deleteContractLine(@PathVariable String id) {
        contractLineService.delete(id);
        return "redirect:/contract-lines";
    }
}