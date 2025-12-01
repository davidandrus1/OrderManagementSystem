package com.example.OrderManagementSystem.Controllers;

import com.example.OrderManagementSystem.Models.UnitOfMeasure;
import com.example.OrderManagementSystem.Services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}