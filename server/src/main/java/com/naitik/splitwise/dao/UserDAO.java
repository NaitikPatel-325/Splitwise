package com.naitik.splitwise.dao;
import com.naitik.splitwise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    public User findByEmail(String email);

}
