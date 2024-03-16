package com.naitik.splitwise.entity;

import com.naitik.splitwise.entity.User;
import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Groups",
    uniqueConstraints = @UniqueConstraint(columnNames={"username"}))
public class Groups {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @Column(name = "groupname")
    private String Groupname;

    @ManyToMany(mappedBy = "groups")
    private List<User> users;
}
