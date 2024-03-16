package com.naitik.splitwise.daojpa;

import com.naitik.splitwise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsernameAndPassword(String username, String password);
}