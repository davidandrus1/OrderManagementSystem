package com.example.OrderManagementSystem.Controllers;

import com.example.OrderManagementSystem.Models.Contract;
import com.example.OrderManagementSystem.Services.ContractService;
import com.example.OrderManagementSystem.Services.ContractTypeService;
import com.example.OrderManagementSystem.Services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contracts")
public class ContractController extends BaseEntityController<Contract, ContractService> {

    private final ContractTypeService contractTypeService;
    private final CustomerService customerService;

    public ContractController(ContractService service, ContractTypeService contractTypeService, CustomerService customerService) {
        super(service);
        this.contractTypeService = contractTypeService;
        this.customerService = customerService;
    }

    @Override
    protected String getListViewName() {
        return "contracts";
    }

    @Override
    protected String getFormViewName() {
        return "contracts-form";
    }

    @Override
    protected String getEntityName() {
        return "Contract";
    }

    @Override
    protected String getBaseUrl() {
        return "contracts";
    }

    @Override
    protected Contract createNewEntity() {
        return new Contract();
    }

    @Override
    @GetMapping({"/{action}", "/{action}/{id}"})
    public String showForm(@PathVariable String action, @PathVariable(required = false) String id, Model model) {
        model.addAttribute("contractTypes", contractTypeService.findAll());
        model.addAttribute("customers", customerService.findAll());
        return super.showForm(action, id, model);
    }

    @GetMapping("/view/{id}")
    public String viewContract(@PathVariable String id, Model model) {
        Contract contract = service.findById(id);
        if (contract == null) {
            return "redirect:/contracts";
        }
        model.addAttribute("contract", contract);
        return "contract-lines";
    }
}