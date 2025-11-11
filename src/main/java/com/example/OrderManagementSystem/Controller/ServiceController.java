package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.ServiceItem;
import com.example.OrderManagementSystem.service.ServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller pentru gestionarea serviciilor (ServiceItem).
 * Urmează același model ca ProductController și ContractLineController.
 */
@Controller
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public String viewAllServices(Model model) {
        model.addAttribute("services", serviceService.getAll());
        return "services/list"; // -> templates/services/list.html
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        // am adăugat 3 parametri: id, name, price
        ServiceItem serviceItem = new ServiceItem(null, "","");
        model.addAttribute("service", serviceItem);
        return "services/create";
    }

    @PostMapping
    public String createService(@ModelAttribute ServiceItem serviceItem) {
        serviceService.save(serviceItem);
        return "redirect:/services";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        ServiceItem serviceItem = serviceService.getById(id);
        model.addAttribute("service", serviceItem);
        return "services/edit";
    }

    @PostMapping("/update")
    public String updateService(@ModelAttribute ServiceItem serviceItem) {
        serviceService.save(serviceItem);
        return "redirect:/services";
    }

    @GetMapping("/delete/{id}")
    public String deleteService(@PathVariable String id) {
        serviceService.delete(id);
        return "redirect:/services";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleValidationError(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("services", serviceService.getAll());
        return "services/list";
    }
}
