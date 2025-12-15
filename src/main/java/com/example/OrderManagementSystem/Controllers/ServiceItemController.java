package com.example.OrderManagementSystem.Controllers;

import com.example.OrderManagementSystem.Models.ServiceItem;
import com.example.OrderManagementSystem.Services.ServiceItemService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/services")
public class ServiceItemController extends BaseEntityController<ServiceItem, ServiceItemService> {

    public ServiceItemController(ServiceItemService service) {
        super(service);
    }

    @Override
    protected String getListViewName() {
        return "services";
    }

    @Override
    protected String getFormViewName() {
        return "service-form";
    }

    @Override
    protected String getEntityName() {
        return "Service Item";
    }

    @Override
    protected String getBaseUrl() {
        return "services";
    }

    @Override
    protected ServiceItem createNewEntity() {
        return new ServiceItem();
    }

    @Override
    @GetMapping
    public String show(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String nameFilter,
            @RequestParam(required = false) String statusFilter,
            Model model) {

        // 🔹 construim sortarea
        Sort sort = Sort.unsorted();
        if (sortBy != null && !sortBy.isBlank()) {
            sort = "desc".equalsIgnoreCase(direction)
                    ? Sort.by(sortBy).descending()
                    : Sort.by(sortBy).ascending();
        }

        // 🔹 filtrare + sortare
        List<ServiceItem> items;

        if (nameFilter != null && !nameFilter.isBlank()
                && statusFilter != null && !statusFilter.isBlank()) {

            items = service.findByNameAndStatus(nameFilter.trim(), statusFilter.trim(), sort);

        } else if (nameFilter != null && !nameFilter.isBlank()) {

            items = service.findByName(nameFilter.trim(), sort);

        } else if (statusFilter != null && !statusFilter.isBlank()) {

            items = service.findByStatus(statusFilter.trim(), sort);

        } else {

            items = service.findAll(sort);
        }

        // 🔹 model
        model.addAttribute("items", items);
        model.addAttribute("url", getBaseUrl());
        model.addAttribute("currentSort", sortBy);
        model.addAttribute("currentDirection", direction != null ? direction : "asc");
        model.addAttribute("nameFilter", nameFilter);
        model.addAttribute("statusFilter", statusFilter);

        return getListViewName();
    }
}
