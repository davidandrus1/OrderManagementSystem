package com.example.OrderManagementSystem.Controllers;

import com.example.OrderManagementSystem.Models.Contract;
import com.example.OrderManagementSystem.Models.ContractLine;
import com.example.OrderManagementSystem.Models.Order;
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
            Model model) {

        List<Contract> items;

        if (sortBy != null && !sortBy.isEmpty()) {
            if ("lines".equalsIgnoreCase(sortBy)) {
                // Sortare manuală după numărul de ContractLines
                items = service.findAll();
                Comparator<Contract> comparator = Comparator.comparingInt(
                        c -> c.getContractLines().size()
                );

                if ("desc".equalsIgnoreCase(direction)) {
                    comparator = comparator.reversed();
                }

                items.sort(comparator);
            } else {
                // Pentru name, status, etc. - sortare normală în MySQL
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

    @GetMapping("/view/{id}")
    public String viewContract(
            @PathVariable String id,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            Model model) {

        Contract contract = service.findById(id);

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
}