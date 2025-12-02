package com.example.OrderManagementSystem.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "contracts_lines")
public class ContractLine extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "sellable_item_id", nullable = false)
    private SellableItem sellableItem;

    @ManyToOne
    @JoinColumn(name = "unit_of_measure_id", nullable = false)
    private UnitOfMeasure unitOfMeasure;

    private Double quantity;

    public ContractLine() {
        this.setPrefix("CL");  // Contract Line prefix
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public SellableItem getSellableItem() {
        return sellableItem;
    }

    public void setSellableItem(SellableItem sellableItem) {
        this.sellableItem = sellableItem;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}