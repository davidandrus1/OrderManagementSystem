package com.example.OrderManagementSystem.Controllers;

import com.example.OrderManagementSystem.Models.BaseModel;
import com.example.OrderManagementSystem.Services.BaseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseEntityController<MODEL extends BaseModel, SERVICE extends BaseService<MODEL, ? extends JpaRepository<MODEL, String>>> {

    protected final SERVICE service;

    public BaseEntityController(SERVICE service) {
        this.service = service;
    }

    protected abstract String getListViewName();
    protected abstract String getFormViewName();
    protected abstract String getEntityName();
    protected abstract String getBaseUrl();
    protected abstract MODEL createNewEntity();

    @GetMapping
    public String show(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            Model model) {

        List<MODEL> items;

        if (sortBy != null && !sortBy.isEmpty()) {
            Sort sort;
            if ("desc".equalsIgnoreCase(direction)) {
                sort = Sort.by(sortBy).descending();
            } else {
                sort = Sort.by(sortBy).ascending();
            }
            items = service.findAll(sort);
        } else {
            items = service.findAll();
        }

        model.addAttribute("items", items);
        model.addAttribute("url", getBaseUrl());
        model.addAttribute("currentSort", sortBy);
        model.addAttribute("currentDirection", direction != null ? direction : "asc");

        return getListViewName();
    }

    @GetMapping({"/{action}", "/{action}/{id}"})
    public String showForm(@PathVariable String action, @PathVariable(required = false) String id, Model model) {

        MODEL entity;

        if (id != null) {
            entity = service.findById(id);
            if (entity == null) {
                return "redirect:/" + getListViewName();
            }
        } else {
            entity = createNewEntity();
        }

        model.addAttribute("item", entity);
        model.addAttribute("action", action);
        model.addAttribute("title", getTitle(action));
        model.addAttribute("caption", getButtonCaption(action));
        model.addAttribute("url", getBaseUrl());

        return getFormViewName();
    }

    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("item") MODEL entity,
            BindingResult bindingResult,
            @RequestParam String action,
            Model model) {

        if ("delete".equals(action)) {
            service.deleteById(entity.getId());
            return "redirect:/" + getBaseUrl();
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("item", entity);
            model.addAttribute("action", action);
            model.addAttribute("title", getTitle(action));
            model.addAttribute("caption", getButtonCaption(action));
            model.addAttribute("url", getBaseUrl());
            return getFormViewName();
        }

        service.save(entity);
        return "redirect:/" + getBaseUrl();
    }

    private String getTitle(String action) {
        return switch (action) {
            case "create" -> "Add New " + getEntityName();
            case "edit" -> "Edit " + getEntityName();
            case "delete" -> "Delete " + getEntityName();
            default -> "";
        };
    }

    private String getButtonCaption(String action) {
        return switch (action) {
            case "create" -> "Create";
            case "edit" -> "Save";
            case "delete" -> "Delete";
            default -> "";
        };
    }
}