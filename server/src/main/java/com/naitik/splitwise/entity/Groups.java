package com.naitik.splitwise.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.naitik.splitwise.entity.User;
import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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

    @JsonIgnore
    @ManyToMany(mappedBy = "groups",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<User> users;

    public String Currency() {
        return Currency;
    }


    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Groups() {

    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<User> getUsers() {
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    public Groups(String groupName, String currency) {
        this.groupName = groupName;
        Currency = currency;
    }

}
