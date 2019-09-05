package com.spl.spl.repository.users;

import com.spl.spl.dto.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {
    @Query(value = "select * from users where users_idx = :idx",nativeQuery = true)
    public Users findByIdx(@Param("idx") Integer idx);

    @Query(value = "select * from users where email = :email",nativeQuery = true)
    Users findByEmail(@Param("email") String email);

    Users findByArea(String area);
}
