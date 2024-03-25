package com.naitik.splitwise.entity;

import com.naitik.splitwise.entity.User;
import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "groups")
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "currency")
    private String Currency;

    @ManyToMany(mappedBy = "groups")
    private List<User> users;



}
