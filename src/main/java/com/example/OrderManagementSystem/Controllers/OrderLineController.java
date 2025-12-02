package com.example.OrderManagementSystem.Controllers;

import com.example.OrderManagementSystem.Models.Order;
import com.example.OrderManagementSystem.Models.OrderLine;
import com.example.OrderManagementSystem.Services.OrderLineService;
import com.example.OrderManagementSystem.Services.OrderService;
import com.example.OrderManagementSystem.Services.SellableItemService;
import com.example.OrderManagementSystem.Services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order-lines")
public class OrderLineController {

    private final OrderLineService service;
    private final OrderService orderService;
    private final SellableItemService sellableItemService;
    private final UnitOfMeasureService unitOfMeasureService;

    public OrderLineController(
            OrderLineService service,
            OrderService orderService,
            SellableItemService sellableItemService,
            UnitOfMeasureService unitOfMeasureService) {
        this.service = service;
        this.orderService = orderService;
        this.sellableItemService = sellableItemService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/create/{orderId}")
    public String showCreateForm(@PathVariable String orderId, Model model) {
        Order order = orderService.findById(orderId);
        if (order == null) {
            return "redirect:/orders";
        }

        OrderLine line = new OrderLine();
        line.setOrder(order);

        model.addAttribute("line", line);
        model.addAttribute("order", order);
        model.addAttribute("action", "create");
        model.addAttribute("title", "Add New Order Line");
        model.addAttribute("caption", "Create");
        model.addAttribute("sellableItems", sellableItemService.findAll());
        model.addAttribute("unitsOfMeasure", unitOfMeasureService.findAll());

        return "order-lines-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        OrderLine line = service.findById(id);
        if (line == null) {
            return "redirect:/orders";
        }

        model.addAttribute("line", line);
        model.addAttribute("order", line.getOrder());
        model.addAttribute("action", "edit");
        model.addAttribute("title", "Edit Order Line");
        model.addAttribute("caption", "Save");
        model.addAttribute("sellableItems", sellableItemService.findAll());
        model.addAttribute("unitsOfMeasure", unitOfMeasureService.findAll());

        return "order-lines-form";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable String id, Model model) {
        OrderLine line = service.findById(id);
        if (line == null) {
            return "redirect:/orders";
        }

        model.addAttribute("line", line);
        model.addAttribute("order", line.getOrder());
        model.addAttribute("action", "delete");
        model.addAttribute("title", "Delete Order Line");
        model.addAttribute("caption", "Delete");
        model.addAttribute("sellableItems", sellableItemService.findAll());
        model.addAttribute("unitsOfMeasure", unitOfMeasureService.findAll());

        return "order-lines-form";
    }

    @PostMapping("/save")
    public String save(
            @RequestParam(required = false) String id,
            @RequestParam String orderId,
            @RequestParam(required = false) String sellableItemId,
            @RequestParam(required = false) String unitOfMeasureId,
            @RequestParam(required = false) Double quantity,
            @RequestParam String action) {

        if ("delete".equals(action)) {
            service.deleteById(id);
        } else {
            OrderLine line;
            if (id != null && !id.isEmpty()) {
                line = service.findById(id);
                if (line == null) {
                    return "redirect:/orders";
                }
            } else {
                line = new OrderLine();
            }

            Order order = orderService.findById(orderId);
            line.setOrder(order);
            line.setSellableItem(sellableItemService.findById(sellableItemId));
            line.setUnitOfMeasure(unitOfMeasureService.findById(unitOfMeasureId));
            line.setQuantity(quantity);

            service.save(line);
        }

        return "redirect:/orders/view/" + orderId;
    }
}