package com.spl.spl.service.timeline;

import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.timeline.Article;
import com.spl.spl.dto.users.Users;
import com.spl.spl.repository.timeline.ArticleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService{

    private ArticleRepository repository;

    @Override
    public Article insert(Groups groups, Users users, String content, boolean nCheck) {
        return repository.save(Article.builder()
                .groups(groups)
                .users(users)
                .content(content)
                .nCheck(nCheck)
                .build());
    }

    @Override
    public List findAll() {
        return repository.findAll();
    }

    @Override
    public List<Article> findByIdx(Integer idx) {
        return repository.findByIdx(idx);
    }
}
