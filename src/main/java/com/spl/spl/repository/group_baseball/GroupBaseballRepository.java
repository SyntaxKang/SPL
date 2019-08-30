package com.spl.spl.repository.group_baseball;

import com.spl.spl.dto.group_baseball.Group_baseball;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupBaseballRepository extends JpaRepository<Group_baseball,Integer> {
}
