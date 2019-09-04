package com.spl.spl.service.photo;

import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.photo.Photo;
import com.spl.spl.dto.timeline.Article;
import com.spl.spl.repository.photo.PhotoRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private PhotoRepository repository;

    @Override
    public List findAll() {
        return repository.findAll();
    }

    @Override
    public Photo insert(Groups groups, Article article, String fileName) {
        return repository.save(Photo.builder()
                .groups(groups)
                .article(article)
                .fileName(fileName)
                .build());
    }

    @Override
    public Photo findByIdx(Integer idx) {
        return repository.findByIdx(idx);
    }
}
