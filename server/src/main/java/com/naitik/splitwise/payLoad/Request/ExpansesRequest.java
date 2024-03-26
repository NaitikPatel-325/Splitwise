package com.naitik.splitwise.payLoad.Request;

import lombok.Data;

@Data
public class ExpansesRequest {
    private String description;
    private Double amount;
    private String date;
    private String paidBy;
    private String group;

    public ExpansesRequest() {
    }

    public ExpansesRequest(String description, Double amount, String date, String paidBy, String group) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.paidBy = paidBy;
        this.group = group;
    }
}
