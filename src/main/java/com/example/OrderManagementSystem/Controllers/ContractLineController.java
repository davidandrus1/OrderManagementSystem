package com.example.OrderManagementSystem.Controllers;

import com.example.OrderManagementSystem.Models.Contract;
import com.example.OrderManagementSystem.Models.ContractLine;
import com.example.OrderManagementSystem.Models.SellableItem;
import com.example.OrderManagementSystem.Models.UnitOfMeasure;
import com.example.OrderManagementSystem.Services.ContractLineService;
import com.example.OrderManagementSystem.Services.ContractService;
import com.example.OrderManagementSystem.Services.SellableItemService;
import com.example.OrderManagementSystem.Services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contract-lines")
public class ContractLineController {

    private final ContractLineService service;
    private final ContractService contractService;
    private final SellableItemService sellableItemService;
    private final UnitOfMeasureService unitOfMeasureService;

    public ContractLineController(
            ContractLineService service,
            ContractService contractService,
            SellableItemService sellableItemService,
            UnitOfMeasureService unitOfMeasureService) {
        this.service = service;
        this.contractService = contractService;
        this.sellableItemService = sellableItemService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/create/{contractId}")
    public String showCreateForm(@PathVariable String contractId, Model model) {
        Contract contract = contractService.findById(contractId);
        if (contract == null) {
            return "redirect:/contracts";
        }

        ContractLine line = new ContractLine();
        line.setContract(contract);

        model.addAttribute("line", line);
        model.addAttribute("contract", contract);
        model.addAttribute("action", "create");
        model.addAttribute("title", "Add New Contract Line");
        model.addAttribute("caption", "Create");
        model.addAttribute("sellableItems", sellableItemService.findAll());
        model.addAttribute("unitsOfMeasure", unitOfMeasureService.findAll());

        return "contract-lines-form";
    }

    // EDIT - afișează formularul pentru editare
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        ContractLine line = service.findById(id);
        if (line == null) {
            return "redirect:/contracts";
        }

        model.addAttribute("line", line);
        model.addAttribute("contract", line.getContract());
        model.addAttribute("action", "edit");
        model.addAttribute("title", "Edit Contract Line");
        model.addAttribute("caption", "Save");
        model.addAttribute("sellableItems", sellableItemService.findAll());
        model.addAttribute("unitsOfMeasure", unitOfMeasureService.findAll());

        return "contract-line-form";
    }

    // DELETE - afișează formularul pentru confirmare ștergere
    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable String id, Model model) {
        ContractLine line = service.findById(id);
        if (line == null) {
            return "redirect:/contracts";
        }

        model.addAttribute("line", line);
        model.addAttribute("contract", line.getContract());
        model.addAttribute("action", "delete");
        model.addAttribute("title", "Delete Contract Line");
        model.addAttribute("caption", "Delete");
        model.addAttribute("sellableItems", sellableItemService.findAll());
        model.addAttribute("unitsOfMeasure", unitOfMeasureService.findAll());

        return "contract-lines-form";
    }

    @PostMapping("/save")
    public String save(
            @RequestParam(required = false) String id,
            @RequestParam String contractId,
            @RequestParam(required = false) String sellableItemId,
            @RequestParam(required = false) String unitOfMeasureId,
            @RequestParam(required = false) Double quantity,
            @RequestParam String action) {

        if ("delete".equals(action)) {
            service.deleteById(id);
        } else {
            ContractLine line;
            if (id != null && !id.isEmpty()) {
                // Edit
                line = service.findById(id);
                if (line == null) {
                    return "redirect:/contracts";
                }
            } else {
                // Create
                line = new ContractLine();
            }

            Contract contract = contractService.findById(contractId);
            SellableItem sellableItem = sellableItemService.findById(sellableItemId);
            UnitOfMeasure unitOfMeasure = unitOfMeasureService.findById(unitOfMeasureId);

            line.setContract(contract);
            line.setSellableItem(sellableItem);
            line.setUnitOfMeasure(unitOfMeasure);
            line.setQuantity(quantity);

            service.save(line);
        }

        return "redirect:/contracts/view/" + contractId;
    }

}