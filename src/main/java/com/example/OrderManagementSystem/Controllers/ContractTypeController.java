package com.example.OrderManagementSystem.Controllers;

import com.example.OrderManagementSystem.Models.ContractType;
import com.example.OrderManagementSystem.Models.Customer;
import com.example.OrderManagementSystem.Services.ContractTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "Contract Types";
    }

    @Override
    protected String getBaseUrl() {
        return "contract-types";
    }

    @Override
    protected ContractType createNewEntity() {
        return new ContractType();
    }
}