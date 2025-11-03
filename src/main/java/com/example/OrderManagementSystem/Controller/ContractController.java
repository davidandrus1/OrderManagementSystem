package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.service.ContractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService service;

    public ContractController(ContractService service) {
        this.service = service;
    }

    // GET: toate contractele
    @GetMapping
    public List<Contract> getAllContracts() {
        return service.getAllContracts();
    }

    // GET: contract după ID
    @GetMapping("/{id}")
    public Optional<Contract> getContractById(@PathVariable String id) {
        return service.getContractById(id);
    }

    // POST: adaugă contract nou
    @PostMapping
    public void addContract(@RequestBody Contract contract) {
        service.addContract(contract);
    }

    // PUT: actualizează contract
    @PutMapping("/{id}")
    public void updateContract(@PathVariable String id, @RequestBody Contract updatedContract) {
        service.updateContract(id, updatedContract);
    }

    // DELETE: șterge contract
    @DeleteMapping("/{id}")
    public void deleteContract(@PathVariable String id) {
        service.deleteContract(id);
    }
}
