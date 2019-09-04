package com.spl.spl.repository.timeline;

import com.spl.spl.dto.timeline.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer> {

    @Query(value = "select * from article where groups_idx = :groupIdx",nativeQuery = true)
    public List<Article> findByIdx(@Param("groupIdx")Integer idx);
}
