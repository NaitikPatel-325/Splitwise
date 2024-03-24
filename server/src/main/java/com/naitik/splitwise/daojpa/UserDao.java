package com.naitik.splitwise.daojpa;

import com.naitik.splitwise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    public User findByUsernameAndPassword(String username, String password);

    public User findByEmailAndPassword(String email, String password);
    public User findByUsername(String username);

    public boolean existsByUsername(String username);
    public boolean existsByEmail(String email);
}