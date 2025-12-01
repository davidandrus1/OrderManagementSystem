package com.example.OrderManagementSystem.Controllers;

import com.example.OrderManagementSystem.Models.BaseModel;
import com.example.OrderManagementSystem.Services.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;
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
    public String show(Model model) {
        List<MODEL> items = service.findAll();
        model.addAttribute("items", items);
        model.addAttribute("url", getBaseUrl());
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
    public String save(@ModelAttribute MODEL entity, @RequestParam String action) {

        if (!"delete".equals(action)) {
            service.save(entity);
        } else {
            service.deleteById(entity.getId());
        }

        return "redirect:/" + getListViewName();
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
