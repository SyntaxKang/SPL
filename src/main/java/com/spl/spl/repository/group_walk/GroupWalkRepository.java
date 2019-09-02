package com.spl.spl.repository.group_walk;

import com.spl.spl.dto.group_walk.Group_walk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupWalkRepository  extends JpaRepository<Group_walk,Integer> {


    @Query(value = "select * from group_walk where groups_idx = :groupIdx order by users_idx",nativeQuery = true)
    public List findByGroupIdx(@Param("groupIdx")Integer idx);
}