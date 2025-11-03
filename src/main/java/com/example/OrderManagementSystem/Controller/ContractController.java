package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.Contract;
import com.example.OrderManagementSystem.service.ContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService service;

    public ContractController(ContractService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Contract>> getAllContracts() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable String id) {
        Contract contract = service.getById(id);
        return ResponseEntity.ok(contract);
    }

    @PostMapping
    public ResponseEntity<Contract> addContract(@RequestBody Contract contract) {
        Contract saved = service.save(contract);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contract> updateContract(@PathVariable String id, @RequestBody Contract updatedContract) {
        Contract updated = service.update(id, updatedContract);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContract(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok("Contract with id " + id + " deleted successfully");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
