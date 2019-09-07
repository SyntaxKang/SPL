package com.spl.spl.repository.group_soccer;


import com.spl.spl.dto.group_soccer.Group_soccer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupSoccerRepository extends JpaRepository<Group_soccer,Integer> {

    @Query(value = "select * from group_soccer where groups_idx = :groupIdx order by users_idx",nativeQuery = true)
    public List findByGroupIdx(@Param("groupIdx") Integer idx);
}
