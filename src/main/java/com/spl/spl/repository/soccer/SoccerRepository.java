package com.spl.spl.repository.soccer;


import com.spl.spl.dto.soccer.Soccer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SoccerRepository extends JpaRepository<Soccer,Integer> {

    @Query(value = "select * from soccer where soccer_idx = :soccerIdx",nativeQuery = true)
    Soccer findByIdx(@Param("soccerIdx") Integer idx);
}
