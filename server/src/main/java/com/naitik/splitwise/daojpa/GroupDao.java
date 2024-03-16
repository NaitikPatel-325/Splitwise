package com.naitik.splitwise.daojpa;

import com.naitik.splitwise.entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDao extends JpaRepository<Groups, Integer> {

}
