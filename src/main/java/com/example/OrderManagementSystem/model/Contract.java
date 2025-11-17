package com.example.OrderManagementSystem.model;

public class Contract extends BaseModel{
    public String contractNumber;
    public int contractTypeId;
    public String status;

// Status de tip separat, in package separat

    public Contract() {}

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

}
