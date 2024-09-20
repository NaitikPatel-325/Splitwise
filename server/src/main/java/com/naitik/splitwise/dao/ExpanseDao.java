package com.naitik.splitwise.dao;

import com.naitik.splitwise.entity.Expanse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpanseDao extends JpaRepository<Expanse, Long> {
    @Query("SELECT e FROM Expanse e JOIN FETCH e.paidBy JOIN FETCH e.group WHERE e.group.id = :groupId")
    List<Expanse> findByGroupId(@Param("groupId") Long groupId);

}
