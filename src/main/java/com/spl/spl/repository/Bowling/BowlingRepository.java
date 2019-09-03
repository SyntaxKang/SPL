package com.spl.spl.repository.Bowling;



import com.spl.spl.dto.bowling.Bowling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BowlingRepository extends JpaRepository<Bowling,Integer> {
    @Query(value = "select * from bowling where bowling_idx = :bowlingIdx",nativeQuery = true)
     Bowling findByIdx(@Param("bowlingIdx")int idx);
}
