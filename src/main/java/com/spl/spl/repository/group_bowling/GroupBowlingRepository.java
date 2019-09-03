package com.spl.spl.repository.group_bowling;

import com.spl.spl.dto.group_bowling.Group_bowling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GroupBowlingRepository extends JpaRepository<Group_bowling,Integer> {
    @Query(value = "select * from group_bowling where groups_idx = :groupIdx order by users_idx",nativeQuery = true)
    public List findByGroupIdx(@Param("groupIdx")int idx);
}
