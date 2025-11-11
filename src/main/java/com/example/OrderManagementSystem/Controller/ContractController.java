package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.model.ContractType;
import com.example.OrderManagementSystem.repository.ContractTypeRepository;
import com.example.OrderManagementSystem.service.ContractService;
import com.example.OrderManagementSystem.service.ContractTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService service;

    public ContractController() {
        this.service = new ContractService();

        ContractTypeService contractTypeService = new ContractTypeService();
        contractTypeService.save(new ContractType("Prestari Servicii", "Seller"));
        contractTypeService.save(new ContractType( "Vanzare En-gros", "Customer"));

        this.service.save(new Contract("1", 1, "Active"));
        this.service.save(new Contract("2", 2, "Active"));
        this.service.save(new Contract("3", 1, "Active"));
    }

    @GetMapping
    public String viewAllContracts(Model model) {

        // Se defineste variabila items = service.getAll()
        // Aceasta variabila se transmite catre contracts/list.html
        // items contine o lista de contracte. Fiecare contract are id, contractTypeId, contractNumber, status
        // In fisierul html se foloseste un limbaj numit Thymeleaf
        model.addAttribute("items", service.getAll());
        return "contracts/list";
    }

    //Functie pentru afisarea paginii cu formular pentru un contract nou
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("item", new Contract());
        return "contracts/create";  // View: templates/contracts/create.html
    }

    @PostMapping("/create")
    public String createContract(@ModelAttribute Contract contract) {
        service.save(contract);
        return "redirect:/contracts";
    }

    @GetMapping("/{id}/delete")
    public String confirmDelete(@PathVariable String id, Model model) {
        this.service.getById(id).ifPresent(item -> model.addAttribute("item", item));
        return "contracts/delete"; // pagina de confirmare
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        this.service.delete(id);
        return "redirect:/contracts"; // redirect la lista de elevi
 }
}
