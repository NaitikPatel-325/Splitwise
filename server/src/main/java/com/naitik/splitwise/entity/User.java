package com.naitik.splitwise.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.naitik.splitwise.entity.Groups;
import java.util.List;

@Data
@Entity
@Table(name="user",
        uniqueConstraints= @UniqueConstraint(columnNames={"username"})
)
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
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