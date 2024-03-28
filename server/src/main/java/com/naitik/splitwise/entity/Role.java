package com.naitik.splitwise.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Repository;

@Entity
public class Role {
    public Integer id() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private URole name;

    public URole getName() {
        return name;
    }

    public void setName(URole name) {
        this.name = name;
    }

    public Role() {
    }
    public Role(URole name) {
        this.name = name;
    }
}
