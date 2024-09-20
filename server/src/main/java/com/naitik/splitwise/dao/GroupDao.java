package com.naitik.splitwise.dao;

import com.naitik.splitwise.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GroupDao extends JpaRepository<Group, Long> {
    List<Group> findByUsersEmail(String email);
}
