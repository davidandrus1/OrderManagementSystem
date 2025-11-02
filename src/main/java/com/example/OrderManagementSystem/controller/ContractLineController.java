package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.ContractLine;
import com.example.OrderManagementSystem.model.SellableItem;
import com.example.OrderManagementSystem.model.UnitOfMeasure;
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

    /**
     * Afișează lista tuturor ContractLine-urilor.
     */
    @GetMapping
    public String viewAllContractLines(Model model) {
        model.addAttribute("contractLines", contractLineService.getAll());
        return "contract-lines/list";
    }

    /**
     * Form pentru crearea unui ContractLine nou.
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        ContractLine contractLine = new ContractLine(null, null, null, 0);

        model.addAttribute("contractLine", contractLine);

        // Obține lista de item-uri concrete pentru dropdown
        model.addAttribute("items", sellableItemService.getAll());

        // UnitOfMeasure dacă este enum
//        model.addAttribute("units", UnitOfMeasure.values());

        return "contract-lines/create";
    }

    /**
     * Salvează un ContractLine nou.
     */
    @PostMapping
    public String createContractLine(@ModelAttribute ContractLine contractLine, @RequestParam long itemId) {
        // Setează obiectul SellableItem selectat din listă
        SellableItem item = sellableItemService.getById(itemId);
        contractLine.setItem(item);

        contractLineService.save(contractLine);
        return "redirect:/contract-lines";
    }

    /**
     * Form pentru editarea unui ContractLine existent.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        ContractLine contractLine = contractLineService.getById(id);
        model.addAttribute("contractLine", contractLine);
        model.addAttribute("items", sellableItemService.getAll());
//        model.addAttribute("units", UnitOfMeasure.values());
        return "contract-lines/edit";
    }

    /**
     * Salvează modificările unui ContractLine existent.
     */
    @PostMapping("/update")
    public String updateContractLine(@ModelAttribute ContractLine contractLine, @RequestParam long itemId) {
        SellableItem item = sellableItemService.getById(itemId);
        contractLine.setItem(item);

        contractLineService.save(contractLine);
        return "redirect:/contract-lines";
    }

    /**
     * Șterge un ContractLine după ID.
     */
    @GetMapping("/delete/{id}")
    public String deleteContractLine(@PathVariable String id) {
        contractLineService.delete(id);
        return "redirect:/contract-lines";
    }
}
