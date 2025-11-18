package com.example.OrderManagementSystem.model;

import java.util.ArrayList;
import java.util.List;

public class Contract extends BaseModel {

    private String contractNumber;
    private int contractTypeId;
    private String status;

    private List<ContractLine> contractLines = new ArrayList<>();

    public Contract() {}

    // ---------------------------
    // BUSINESS LOGIC
    // ---------------------------
    public double calculateTotalValue() {
        if (contractLines == null || contractLines.isEmpty()) {
            return 0;
        }

        return contractLines.stream()
                .mapToDouble(line -> line.getQuantity() * line.getItem().getValue())
                .sum();
    }

    // ---------------------------
    // GETTERS & SETTERS
    // ---------------------------
    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public int getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(int contractTypeId) {
        this.contractTypeId = contractTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ContractLine> getContractLines() {
        return contractLines;
    }

    public void setContractLines(List<ContractLine> contractLines) {
        this.contractLines = contractLines;
    }

}
