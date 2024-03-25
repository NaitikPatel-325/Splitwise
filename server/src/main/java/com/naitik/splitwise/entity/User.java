package com.naitik.splitwise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.naitik.splitwise.entity.Groups;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "user_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<Groups> groups;

    public User(String username, String email, String encode) {
        this.username = username;
        this.email = email;
        this.password = encode;
    }
}


//Users Table:
//
//user_id (Primary Key)
//username (Unique)

//email (Unique)
//password (Hashed)
//created_at
//updated_at
//Groups Table:
//
//group_id (Primary Key)
//group_name
//created_by (Foreign Key referencing user_id in Users table)
//created_at
//updated_at
//Group_Members Table:
//
//group_member_id (Primary Key)
//group_id (Foreign Key referencing group_id in Groups table)
//user_id (Foreign Key referencing user_id in Users table)
//joined_at
//updated_at
//Expenses Table:
//
//expense_id (Primary Key)
//group_id (Foreign Key referencing group_id in Groups table)
//paid_by (Foreign Key referencing user_id in Users table)
//amount
//description
//date
//created_at
//updated_at
//Expense_Shares Table:
//
//share_id (Primary Key)
//expense_id (Foreign Key referencing expense_id in Expenses table)
//user_id (Foreign Key referencing user_id in Users table)
//share_amount
//created_at
//updated_at