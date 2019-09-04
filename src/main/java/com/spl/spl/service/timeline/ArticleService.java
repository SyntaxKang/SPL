package com.spl.spl.service.timeline;

import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.timeline.Article;
import com.spl.spl.dto.users.Users;

import java.util.List;

public interface ArticleService {
    public Article insert(Groups groups, Users users,String content,boolean nCheck);

    public List findAll();

    public List<Article> findByIdx(Integer idx);
}
