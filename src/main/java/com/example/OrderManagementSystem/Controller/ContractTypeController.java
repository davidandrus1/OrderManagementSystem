package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.ContractType;
import com.example.OrderManagementSystem.service.ContractTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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

    @PostMapping
    public ResponseEntity<ContractType> addContractType(@RequestBody ContractType contractType) {
        ContractType saved = service.save(contractType);
        return ResponseEntity.ok(saved);
    }

}
