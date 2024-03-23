package com.naitik.splitwise.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.naitik.splitwise.entity.Groups;
import java.util.List;

@Data
@Entity
@Table(name = "users") // Changed table name to lowercase and plural form
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // Changed column name to snake_case
    private int id;

    @Column(name = "name") // Changed column name to snake_case
    private String Name; // Changed variable name to camelCase

    @Column(name = "username", unique = true) // Added unique constraint
    private String username;

    @Column(name = "email", unique = true) // Added unique constraint
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<Groups> groups;

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