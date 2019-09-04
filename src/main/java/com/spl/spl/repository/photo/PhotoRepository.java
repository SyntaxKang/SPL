package com.spl.spl.repository.photo;

import com.spl.spl.dto.photo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Integer> {

    @Query(value = "select * from photo where article_idx = :articleIdx",nativeQuery = true)
    public Photo findByIdx(@Param("articleIdx")Integer idx);

}
