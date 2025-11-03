package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.ContractType;
import com.example.OrderManagementSystem.service.ContractTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contractTypes")
public class ContractTypeController {

    private final ContractTypeService service;

    public ContractTypeController(ContractTypeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ContractType>> getAllContractTypes() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractType> getContractTypeById(@PathVariable String id) {
        ContractType type = service.getById(id);
        return ResponseEntity.ok(type);
    }

    @PostMapping
    public ResponseEntity<ContractType> addContractType(@RequestBody ContractType contractType) {
        ContractType saved = service.save(contractType);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContractType> updateContractType(@PathVariable String id, @RequestBody ContractType updatedContractType) {
        ContractType updated = service.update(id, updatedContractType);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContractType(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok("ContractType with id " + id + " deleted successfully");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
