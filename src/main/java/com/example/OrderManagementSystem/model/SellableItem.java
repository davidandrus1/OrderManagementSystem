package com.example.OrderManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        visible = true,
        defaultImpl = ProductItem.class // optional, dacă nu se găsește type
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProductItem.class, name = "product"),
        @JsonSubTypes.Type(value = ServiceItem.class, name = "service")
})

public abstract class SellableItem extends BaseModel{

    public String name;

    public SellableItem() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
