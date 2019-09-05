package com.spl.spl.service.timeline;

import com.spl.spl.dto.timeline.Comment;

public interface CommentService {
    public Comment findByIdx(Integer idx);

    Comment insert(String content, int parent, int article, int users);
}
