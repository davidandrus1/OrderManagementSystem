package com.example.OrderManagementSystem.Controllers;

import com.example.OrderManagementSystem.Models.ContractType;
import com.example.OrderManagementSystem.Services.ContractTypeService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/contract-types")
public class ContractTypeController extends BaseEntityController<ContractType, ContractTypeService> {

    public ContractTypeController(ContractTypeService service) {
        super(service);
    }

    @Override
    protected String getListViewName() {
        return "contract-types";
    }

    @Override
    protected String getFormViewName() {
        return "contract-types-form";
    }

    @Override
    protected String getEntityName() {
        return "Contract Type";
    }

    @Override
    protected String getBaseUrl() {
        return "contract-types";
    }

    @Override
    protected ContractType createNewEntity() {
        return new ContractType();
    }

    @Override
    @GetMapping
    public String show(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String nameFilter,
            @RequestParam(required = false) String typeFilter,
            Model model) {

        Sort sort = Sort.unsorted();
        if (sortBy != null && !sortBy.isBlank()) {
            sort = "desc".equalsIgnoreCase(direction)
                    ? Sort.by(sortBy).descending()
                    : Sort.by(sortBy).ascending();
        }

        List<ContractType> items;

        if (nameFilter != null && !nameFilter.isBlank() &&
                typeFilter != null && !typeFilter.isBlank()) {
            items = service.findByNameAndType(nameFilter.trim(), typeFilter.trim(), sort);
        } else if (nameFilter != null && !nameFilter.isBlank()) {
            items = service.findByName(nameFilter.trim(), sort);
        } else if (typeFilter != null && !typeFilter.isBlank()) {
            items = service.findByType(typeFilter.trim(), sort);
        } else {
            items = service.findAll(sort);
        }

        model.addAttribute("items", items);
        model.addAttribute("url", getBaseUrl());
        model.addAttribute("currentSort", sortBy);
        model.addAttribute("currentDirection", direction != null ? direction : "asc");
        model.addAttribute("nameFilter", nameFilter);
        model.addAttribute("typeFilter", typeFilter);

        return getListViewName();
    }
}