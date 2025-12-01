package com.example.OrderManagementSystem.Controllers;
import com.example.OrderManagementSystem.Models.ProductItem;
import com.example.OrderManagementSystem.Services.ProductItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "Product";
    }

    @Override
    protected String getBaseUrl() {
        return "products";
    }

    @Override
    protected ProductItem createNewEntity() {
        return new ProductItem();
    }
}
