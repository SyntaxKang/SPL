package com.spl.spl.repository.group_baseball;

import com.spl.spl.dto.group_baseball.Group_baseball;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupBaseballRepository extends JpaRepository<Group_baseball,Integer> {

    @Query(value = "select * from group_baseball where groups_idx = :groupIdx order by users_idx",nativeQuery = true)
    public List findByGroupIdx(@Param("groupIdx")Integer idx);
}
