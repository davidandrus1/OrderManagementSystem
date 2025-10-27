package com.example.OrderManagementSystem.model;

public class Contract {
    public Long id;
    public String contractNumber;
    public Long contractTypeId;
    public String status;
    public Contract (Long id, String contractNumber, Long contractTypeId, String status) {
        this.id = id;
        this.contractNumber = contractNumber;
        this.contractTypeId = contractTypeId;
        this.status = status;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Long getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(Long contractTypeId) {
        this.contractTypeId = contractTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
