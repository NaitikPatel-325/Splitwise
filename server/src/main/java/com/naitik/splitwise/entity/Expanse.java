package com.naitik.splitwise.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "expanse")
public class Expanse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "contributedExpanses")
    private List<User> contributors;
    private String description;

    private Double amount;

    private String date;


    @ManyToOne(cascade = CascadeType.ALL)
    private User paidBy;

    @ManyToOne(cascade = CascadeType.ALL)
    private Groups group;

    public String description() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long id() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String date() {
        return date;
    }

    public User paidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }



    public Expanse() {
    }

    public Expanse(String description, Double amount, String date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public Expanse(String description, Double amount, String date, User paidBy, Groups group) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.paidBy = paidBy;
        this.group = group;
    }


    public Groups group() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }
}
