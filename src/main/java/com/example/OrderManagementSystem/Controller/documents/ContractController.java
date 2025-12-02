package com.example.OrderManagementSystem.controller.documents;

import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.service.basedata.ContractTypeService;
import com.example.OrderManagementSystem.service.documents.ContractService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService service;
    private final ContractTypeService contractTypeService;

    public ContractController(ContractService service, ContractTypeService contractTypeService) {
        this.service = service;
        this.contractTypeService = contractTypeService;
    }

    @GetMapping
    public String viewAllContracts(Model model) {


        model.addAttribute("items", service.findAll());
        return "contracts/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("item", new Contract());
        return "contracts/create";  // View: templates/contracts/create.html
    }
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
