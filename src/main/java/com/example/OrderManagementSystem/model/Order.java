package com.example.OrderManagementSystem.model;

import java.util.ArrayList;
import java.util.List;

public class Order extends BaseModel{


    private String name;

    private Customer customer;

    private Contract contract;

    private List<OrderLine> orderLines = new ArrayList<>();

    public Order(){};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

//    public boolean validateAgainstContract() {
//        if (contract == null) return true; // order poate fi fără contract
//
//        for (OrderLine ol : orderLines) {
//            ContractLine cl = contract.getContractLines().stream()
//                    .filter(c -> c.getItem().getId().equals(ol.getItem().getId()))
//                    .findFirst()
//                    .orElse(null);
//
//            if (cl == null) return false;
//            if (!cl.getUnit().getId().equals(ol.getUnit().getId())) return false;
//            if (ol.getQuantity() > cl.getQuantity()) return false;
//        }
//        return true;
//    }
}
