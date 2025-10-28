package com.example.OrderManagementSystem.model;

public class Contract {
    public String id;

    public String contractNumber;

    public String contractTypeId;

    public String status;

    public Contract (String id, String contractNumber, String contractTypeId, String status) {
        this.id = id;
        this.contractNumber = contractNumber;
        this.contractTypeId = contractTypeId;
        this.status = status;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(String contractTypeId) {
        this.contractTypeId = contractTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
