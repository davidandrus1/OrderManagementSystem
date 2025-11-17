package com.example.OrderManagementSystem.controller.documents;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order-lines")
public class OrderLineController {
//
//    private final OrderLineService orderLineService;
//    private final SellableItemService sellableItemService;
//    private final UnitOfMeasureService unitOfMeasureService;
//
//    public OrderLineController(OrderLineService orderLineService, SellableItemService sellableItemService, UnitOfMeasureService unitOfMeasureService) {
//        this.orderLineService = orderLineService;
//        this.sellableItemService = sellableItemService;
//        this.unitOfMeasureService = unitOfMeasureService;
//    }
//
//    @GetMapping
//    public String viewAllOrderLines(Model model) {
//        model.addAttribute("orderLines", orderLineService.getAll());
//        return "order-lines/list";
//    }
//
//    @GetMapping("/new")
//    public String showCreateForm(Model model) {
//        // Item și unit nu sunt null ca să nu dea eroare la Thymeleaf
//        OrderLine orderLine = new OrderLine(null, new SellableItem(),UnitOfMeasure.values()[0],0);
//
//        model.addAttribute("orderLine", orderLine);
//        model.addAttribute("items", sellableItemService.getAll());
//        model.addAttribute("units", unitOfMeasureService.getAll()); // sau unitOfMeasureService.getAll() dacă e clasă
//        return "order-lines/create";
//    }
//
//    @PostMapping("/create")
//    public String createOrderLine(@ModelAttribute OrderLine orderLine,
//                                  @RequestParam String itemId) {
//        orderLine.setItem(sellableItemService.getById(itemId));
//        orderLineService.save(orderLine);
//        return "redirect:/order-lines";
//    }
//
//
//    @GetMapping("/edit/{id}")
//    public String showEditForm(@PathVariable String id, Model model) {
//        return orderLineService.getById(id)
//                .map(orderLine -> {
//                    model.addAttribute("orderLine", orderLine);
//                    model.addAttribute("items", sellableItemService.getAll());
//                    return "order-lines/edit";
//                })
//                .orElse("redirect:/order-lines");
//    }
//
//    @PostMapping("/update")
//    public String updateOrderLine(@ModelAttribute OrderLine orderLine,
//                                  @RequestParam String itemId) {
//        orderLine.setItem(sellableItemService.getById(itemId));
//        orderLineService.save(orderLine);
//        return "redirect:/order-lines";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteOrderLine(@PathVariable String id) {
//        orderLineService.delete(id);
//        return "redirect:/order-lines";
//    }
}
