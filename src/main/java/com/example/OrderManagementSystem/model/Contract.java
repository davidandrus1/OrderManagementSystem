package com.example.OrderManagementSystem.model;

import com.example.OrderManagementSystem.repository.ContractTypeRepository;

public class Contract {
    public  String id;
    public String contractNumber;
    public int contractTypeId;
    public String status;

// Status de tip separat, in package separat

    public Contract() {}

    public Contract(String contractNumber, int contractTypeId, String status){
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
