package com.naitik.splitwise.payLoad.Request;

import com.naitik.splitwise.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class ExpansesRequest {
    private int id;
    private String description;
    private Double amount;
    private String date;
    private String paidBy;
    private String group;
    private List<Integer> users;

    public ExpansesRequest() {
    }

    public ExpansesRequest(String description, Double amount, String date, String paidBy, String group) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.paidBy = paidBy;
        this.group = group;
    }

    public ExpansesRequest(int id,String description, Double amount, String date, List<Integer> users) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.users = users;
    }

}
