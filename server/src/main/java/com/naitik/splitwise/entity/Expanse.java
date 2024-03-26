package com.naitik.splitwise.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "expanse")
public class Expanse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Double amount;

    private String date;

    @ManyToOne(cascade = CascadeType.ALL)
    private User paidBy;

    @ManyToOne(cascade = CascadeType.ALL)
    private Groups group;

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



}
