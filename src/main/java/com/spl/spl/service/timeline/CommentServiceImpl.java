package com.spl.spl.service.timeline;

import com.spl.spl.dto.timeline.Comment;
import com.spl.spl.repository.timeline.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private CommentRepository repository;

    @Override
    public Comment findByIdx(Integer idx) {
        return repository.findByIdx(idx);
    }

    @Override
    public Comment insert(String content, int parent, int articleIdx, int usersIdx)
    {
        return repository.save(Comment.builder()
                .content(content)
                .parent(parent)
                .articleIdx(articleIdx)
                .usersIdx(usersIdx)
                .build());
    }
}
