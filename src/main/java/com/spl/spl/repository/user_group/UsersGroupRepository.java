package com.spl.spl.repository.user_group;

import com.spl.spl.dto.user_group.Users_group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersGroupRepository extends JpaRepository<Users_group, Integer> {

    @Query(value = "select * from Users_group where groups_idx = :groupIdx", nativeQuery = true)
    List findByGroupIdx(@Param("groupIdx") int groupsIdx);

    @Query(value = "select * from Users_group where users_idx = :usersIdx", nativeQuery = true)
    List findByUsersIdx(@Param("usersIdx") Integer usersIdx);
}
