package com.spl.spl.repository.baseball;

import com.spl.spl.dto.baseball.Baseball;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseballRepository extends JpaRepository<Baseball, Integer> {

//    @Query(value = "insert into baseball(atbat,hit,homerun,rbi,score,ball,strike) values(:atbat," +
//            ":hit,:homerun,:rbi,:score,:ball,:strike)",nativeQuery = true)
//    public Baseball insert(@Param("atbat") int atbat,@Param("hit") int hit,@Param("homerun") int homerun,@Param("rbi") int rbi
//    ,@Param("score") int score,@Param("ball") int ball,@Param("strike") int strike);
}
