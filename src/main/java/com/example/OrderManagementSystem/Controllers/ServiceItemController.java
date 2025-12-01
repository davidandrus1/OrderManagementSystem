package com.example.OrderManagementSystem.Controllers;

import com.example.OrderManagementSystem.Models.ServiceItem;
import com.example.OrderManagementSystem.Services.ServiceItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "services-form";
    }

    @Override
    protected String getEntityName() {
        return "Service";
    }

    @Override
    protected String getBaseUrl() {
        return "services";
    }

    @Override
    protected ServiceItem createNewEntity() {
        return new ServiceItem();
    }
}