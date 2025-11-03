package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.ContractType;
import com.example.OrderManagementSystem.service.ContractTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contractTypes")
public class ContractTypeController {

    private final ContractTypeService service;

    public ContractTypeController(ContractTypeService service) {
        this.service = service;
    }

    // GET: toate tipurile de contracte
    @GetMapping
    public List<ContractType> getAllContractTypes() {
        return service.getAllContractTypes();
    }

    // GET: tip de contract după ID
    @GetMapping("/{id}")
    public Optional<ContractType> getContractTypeById(@PathVariable String id) {
        return service.getContractTypeById(id);
    }

    // POST: adaugă un nou tip de contract
    @PostMapping
    public void addContractType(@RequestBody ContractType contractType) {
        service.addContractType(contractType);
    }

    // PUT: actualizează un tip de contract
    @PutMapping("/{id}")
    public void updateContractType(@PathVariable String id, @RequestBody ContractType updatedContractType) {
        service.updateContractType(id, updatedContractType);
    }

    // DELETE: șterge un tip de contract
    @DeleteMapping("/{id}")
    public void deleteContractType(@PathVariable String id) {
        service.deleteContractType(id);
    }
}
