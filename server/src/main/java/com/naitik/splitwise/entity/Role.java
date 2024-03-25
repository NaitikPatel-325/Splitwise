package com.naitik.splitwise.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private URole name;

    public Role() {
    }
    public Role(URole name) {
        this.name = name;
    }
}
