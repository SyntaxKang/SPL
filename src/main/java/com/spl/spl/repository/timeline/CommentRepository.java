package com.spl.spl.repository.timeline;

import com.spl.spl.dto.timeline.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    @Query(value = "select * from comment where article_idx = :articleIdx",nativeQuery = true)
     Comment findByIdx(@Param("articleIdx")Integer idx);


}
