package com.example.OrderManagementSystem.Controllers;

import com.example.OrderManagementSystem.Models.UnitOfMeasure;
import com.example.OrderManagementSystem.Services.UnitOfMeasureService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/units-of-measures")
public class UnitOfMeasureController extends BaseEntityController<UnitOfMeasure, UnitOfMeasureService> {

    public UnitOfMeasureController(UnitOfMeasureService service) {
        super(service);
    }

    @Override
    protected String getListViewName() {
        return "units-of-measures";
    }

    @Override
    protected String getFormViewName() {
        return "units-of-measures-form";
    }

    @Override
    protected String getEntityName() {
        return "Units of Measures";
    }

    @Override
    protected String getBaseUrl() {
        return "units-of-measures";
    }

    @Override
    protected UnitOfMeasure createNewEntity() {
        return new UnitOfMeasure();
    }

    @Override
    @GetMapping
    public String show(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String nameFilter,
            @RequestParam(required = false) String symbolFilter,
            Model model) {

        Sort sort = Sort.unsorted();
        if (sortBy != null && !sortBy.isBlank()) {
            sort = "desc".equalsIgnoreCase(direction)
                    ? Sort.by(sortBy).descending()
                    : Sort.by(sortBy).ascending();
        }

        List<UnitOfMeasure> items;

        if (nameFilter != null && !nameFilter.isBlank()
                && symbolFilter != null && !symbolFilter.isBlank()) {

            items = service.findByNameAndSymbol(nameFilter.trim(), symbolFilter.trim(), sort);

        } else if (nameFilter != null && !nameFilter.isBlank()) {

            items = service.findByName(nameFilter.trim(), sort);

        } else if (symbolFilter != null && !symbolFilter.isBlank()) {

            items = service.findBySymbol(symbolFilter.trim(), sort);

        } else {

            items = service.findAll(sort);
        }

        model.addAttribute("items", items);
        model.addAttribute("url", getBaseUrl());
        model.addAttribute("currentSort", sortBy);
        model.addAttribute("currentDirection", direction != null ? direction : "asc");
        model.addAttribute("nameFilter", nameFilter);
        model.addAttribute("symbolFilter", symbolFilter);

        return getListViewName();
    }
}
