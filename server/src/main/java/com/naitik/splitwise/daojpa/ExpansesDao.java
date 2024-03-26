package com.naitik.splitwise.daojpa;

import com.naitik.splitwise.entity.Expanse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpansesDao extends JpaRepository<Expanse, Long> {


    List<Expanse> findAllByGroupId(Long groupId);


}

