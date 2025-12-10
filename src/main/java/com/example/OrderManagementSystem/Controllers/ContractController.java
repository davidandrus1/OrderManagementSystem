package com.example.OrderManagementSystem.Controllers;

import com.example.OrderManagementSystem.Models.Contract;
import com.example.OrderManagementSystem.Models.Order;
import com.example.OrderManagementSystem.Services.ContractService;
import com.example.OrderManagementSystem.Services.ContractTypeService;
import com.example.OrderManagementSystem.Services.CustomerService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

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
    @GetMapping
    public String show(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            Model model) {

        List<Contract> items;

        if (sortBy != null && !sortBy.isEmpty()) {
            if ("lines".equalsIgnoreCase(sortBy)) {
                items = service.findAll();
                Comparator<Contract> comparator = Comparator.comparingInt(o -> o.getContractLines().size());

                if ("desc".equalsIgnoreCase(direction)) {
                    comparator = comparator.reversed();
                }

                items.sort(comparator);
            } else if ("customer.name".equalsIgnoreCase(sortBy)) {
                items = service.findAll();
                Comparator<Contract> comparator = Comparator.comparing(
                        o -> o.getCustomer() != null ? o.getCustomer().getName() : "",
                        String.CASE_INSENSITIVE_ORDER
                );

                if ("desc".equalsIgnoreCase(direction)) {
                    comparator = comparator.reversed();
                }

                items.sort(comparator);
            } else {
                Sort sort = "desc".equalsIgnoreCase(direction)
                        ? Sort.by(sortBy).descending()
                        : Sort.by(sortBy).ascending();
                items = service.findAll(sort);
            }
        } else {
            items = service.findAll();
        }

        model.addAttribute("items", items);
        model.addAttribute("url", getBaseUrl());
        model.addAttribute("currentSort", sortBy);
        model.addAttribute("currentDirection", direction != null ? direction : "asc");

        return getListViewName();
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

        List<Contract> allContracts = service.findAll();
        Contract contract = service.findById(id);

        if (contract == null) {
            return "redirect:/contracts";
        }
        model.addAttribute("contract", contract);
        model.addAttribute("lines", contract.getContractLines());
        model.addAttribute("url", "contract-lines");
        return "contract-lines";
    }
}