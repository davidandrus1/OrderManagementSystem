package com.example.OrderManagementSystem.controller.documents;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contracts")
public class ContractController {
//
//    private final ContractService service;
//
//    public ContractController() {
//        this.service = new ContractService();
//
//    }
//
//    @GetMapping
//    public String viewAllContracts(Model model) {
//
//        // Se defineste variabila items = service.getAll()
//        // Aceasta variabila se transmite catre contracts/list.html
//        // items contine o lista de contracte. Fiecare contract are id, contractTypeId, contractNumber, status
//        // In fisierul html se foloseste un limbaj numit Thymeleaf
//        model.addAttribute("items", service.getAll());
//        return "contracts/list";
//    }
//
//    //Functie pentru afisarea paginii cu formular pentru un contract nou
//    @GetMapping("/new")
//    public String showCreateForm(Model model) {
//        model.addAttribute("item", new Contract());
//        return "contracts/create";  // View: templates/contracts/create.html
//    }
//
//    @PostMapping("/create")
//    public String createContract(@ModelAttribute Contract contract) {
//        service.save(contract);
//        return "redirect:/contracts";
//    }
//
//    @GetMapping("/{id}/delete")
//    public String confirmDelete(@PathVariable String id, Model model) {
//        this.service.getById(id).ifPresent(item -> model.addAttribute("item", item));
//        return "contracts/delete"; // pagina de confirmare
//    }
//
//    @PostMapping("/{id}/delete")
//    public String delete(@PathVariable String id) {
//        this.service.delete(id);
//        return "redirect:/contracts"; // redirect la lista de elevi
// }
}
