package com.naitik.splitwise.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;

@Entity
@Table(name = "expanse")
public class Expanse {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToMany(mappedBy = "contributedExpanses")
    private List<User> contributors;
    private String description;

    private Double amount;

    private String date;

    @JsonIgnore
    @ManyToOne()
    private User paidBy;


    @JsonIgnore
    @ManyToOne()
    private Groups group;


    public Expanse() {
    }

    public Expanse(String description, Double amount, String date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public Expanse(String description, Double amount, String date, User paidBy, Groups group,List<User> contributors) {
        this.contributors = contributors;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.paidBy = paidBy;
        this.group = group;
    }

    public List<User> getContributors() {
        if (contributors == null)
            contributors = new ArrayList<>();

        return contributors;
    }

    public List<User> getCountributors() {
        return contributors;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public User getPaidBy() {
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



    public void setContributors(List<User> contributors) {

        this.contributors = contributors;
    }




    public Groups getGroup() {

        return group;
    }

    public void setGroup(Groups group) {

        this.group = group;
    }
}
