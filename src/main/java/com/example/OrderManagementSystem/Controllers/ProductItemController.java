package com.example.OrderManagementSystem.Controllers;

import com.example.OrderManagementSystem.Models.ProductItem;
import com.example.OrderManagementSystem.Services.ProductItemService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductItemController extends BaseEntityController<ProductItem, ProductItemService> {

    public ProductItemController(ProductItemService service) {
        super(service);
    }

    @Override
    protected String getListViewName() {
        return "products";
    }

    @Override
    protected String getFormViewName() {
        return "products-form";
    }

    @Override
    protected String getEntityName() {
        return "Product Item";
    }

    @Override
    protected String getBaseUrl() {
        return "products";
    }

    @Override
    protected ProductItem createNewEntity() {
        return new ProductItem();
    }

    @Override
    @GetMapping
    public String show(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String nameFilter,
            @RequestParam(required = false) String valueFilter,
            Model model) {

        Sort sort = Sort.unsorted();
        if (sortBy != null && !sortBy.isBlank()) {
            sort = "desc".equalsIgnoreCase(direction)
                    ? Sort.by(sortBy).descending()
                    : Sort.by(sortBy).ascending();
        }

        List<ProductItem> items;

        if (nameFilter != null && !nameFilter.isBlank() && valueFilter != null) {
            items = service.findByNameAndValue(nameFilter.trim(), Double.valueOf(valueFilter), sort);
        } else if (nameFilter != null && !nameFilter.isBlank()) {
            items = service.findByName(nameFilter.trim(), sort);
        } else if (valueFilter != null) {
            items = service.findByValue(Double.valueOf(valueFilter), sort);
        } else {
            items = service.findAll(sort);
        }
        model.addAttribute("items", items);
        model.addAttribute("url", getBaseUrl());
        model.addAttribute("currentSort", sortBy);
        model.addAttribute("currentDirection", direction != null ? direction : "asc");
        model.addAttribute("nameFilter", nameFilter);
        model.addAttribute("valueFilter", valueFilter);

        return getListViewName();
    }
}