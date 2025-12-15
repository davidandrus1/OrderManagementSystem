package com.example.OrderManagementSystem.Controllers;

import com.example.OrderManagementSystem.Models.Contract;
import com.example.OrderManagementSystem.Models.ContractLine;
import com.example.OrderManagementSystem.Services.ContractService;
import com.example.OrderManagementSystem.Services.ContractTypeService;
import com.example.OrderManagementSystem.Services.CustomerService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
            @RequestParam(required = false) String nameFilter,
            @RequestParam(required = false) String statusFilter,
            Model model) {

        Sort sort = Sort.unsorted();
        if (sortBy != null && !sortBy.isBlank()) {
            sort = "desc".equalsIgnoreCase(direction)
                    ? Sort.by(sortBy).descending()
                    : Sort.by(sortBy).ascending();
        }

        List<Contract> items;

        if (nameFilter != null && !nameFilter.isBlank() &&
                statusFilter != null && !statusFilter.isBlank()) {
            items = service.findByNameAndStatus(nameFilter.trim(), statusFilter.trim(), sort);
        } else if (nameFilter != null && !nameFilter.isBlank()) {
            items = service.findByName(nameFilter.trim(), sort);
        } else if (statusFilter != null && !statusFilter.isBlank()) {
            items = service.findByStatus(statusFilter.trim(), sort);
        } else {
            items = service.findAll(sort);
        }

        model.addAttribute("items", items);
        model.addAttribute("url", getBaseUrl());
        model.addAttribute("currentSort", sortBy);
        model.addAttribute("currentDirection", direction != null ? direction : "asc");
        model.addAttribute("nameFilter", nameFilter);
        model.addAttribute("statusFilter", statusFilter);

        return getListViewName();
    }

    @Override
    @GetMapping({"/{action}", "/{action}/{id}"})
    public String showForm(@PathVariable String action, @PathVariable(required = false) String id, Model model) {
        Contract entity;

        if (id != null) {
            List<Contract> allContracts = service.findAll();
            entity = allContracts.stream()
                    .filter(c -> c.getId().equals(id))
                    .findFirst()
                    .orElse(null);

            if (entity == null) {
                return "redirect:/contracts";
            }
        } else {
            entity = createNewEntity();
        }

        model.addAttribute("item", entity);
        model.addAttribute("action", action);
        model.addAttribute("title", getTitle(action));
        model.addAttribute("caption", getButtonCaption(action));
        model.addAttribute("url", getBaseUrl());
        model.addAttribute("contractTypes", contractTypeService.findAll());
        model.addAttribute("customers", customerService.findAll());

        return "contracts-form";
    }

    @GetMapping("/view/{id}")
    public String viewContract(
            @PathVariable String id,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            Model model) {

        List<Contract> allContracts = service.findAll();
        Contract contract = allContracts.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (contract == null) {
            return "redirect:/contracts";
        }

        List<ContractLine> lines = new ArrayList<>(contract.getContractLines());

        if (sortBy != null && !sortBy.isEmpty()) {
            Comparator<ContractLine> comparator = null;

            switch (sortBy.toLowerCase()) {
                case "item":
                    comparator = Comparator.comparing(
                            line -> line.getSellableItem() != null ? line.getSellableItem().getName() : "",
                            String.CASE_INSENSITIVE_ORDER
                    );
                    break;
                case "quantity":
                    comparator = Comparator.comparingDouble(ContractLine::getQuantity);
                    break;
            }

            if (comparator != null) {
                if ("desc".equalsIgnoreCase(direction)) {
                    comparator = comparator.reversed();
                }
                lines.sort(comparator);
            }
        }

        model.addAttribute("contract", contract);
        model.addAttribute("lines", lines);
        model.addAttribute("url", "contract-lines");
        model.addAttribute("currentSort", sortBy);
        model.addAttribute("currentDirection", direction != null ? direction : "asc");

        return "contract-lines";
    }

    public String getTitle(String action) {
        return switch (action) {
            case "create" -> "Add New Contract";
            case "edit" -> "Edit Contract";
            case "delete" -> "Delete Contract";
            default -> "";
        };
    }

    public String getButtonCaption(String action) {
        return switch (action) {
            case "create" -> "Create";
            case "edit" -> "Save";
            case "delete" -> "Delete";
            default -> "";
        };
    }
}