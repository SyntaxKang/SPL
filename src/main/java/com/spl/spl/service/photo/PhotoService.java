package com.spl.spl.service.photo;

import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.photo.Photo;
import com.spl.spl.dto.timeline.Article;

import java.util.List;

public interface PhotoService {
    public Photo insert(Groups groups, Article article,String fileName);

    public List findAll();

    public Photo findByIdx(Integer idx);
}
