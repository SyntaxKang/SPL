package com.spl.spl.repository.group;

import com.spl.spl.dto.group.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsRepository extends JpaRepository<Groups,Integer> {

    @Query(value = "select * from groups where groups_idx = :groupsIdx",nativeQuery = true)
    public Groups findByIdx(@Param("groupsIdx")Integer idx);
}
