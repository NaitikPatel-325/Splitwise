package com.naitik.splitwise.daojpa;

import com.naitik.splitwise.entity.Role;
import com.naitik.splitwise.entity.URole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Roledao extends JpaRepository<Role, Long> {
    Optional<Role> findByName(URole name);
}
