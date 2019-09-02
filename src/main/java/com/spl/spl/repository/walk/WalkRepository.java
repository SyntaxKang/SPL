package com.spl.spl.repository.walk;

import com.spl.spl.dto.walk.Walk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalkRepository extends JpaRepository<Walk, Integer> {

}
