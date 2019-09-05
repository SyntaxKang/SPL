package com.spl.spl.dto.timeline;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmt_idx")
    public Integer cmtIdx;


    @Column(name = "content")
    public String content;

    @Column(name = "parent")
    public int parent;


    @Column(name = "article_idx")
    public int articleIdx;


    @Column(name = "users_idx")
    public int usersIdx;

    @Column(name = "cmt_date")
    public LocalDateTime cmtdate;


    @Builder
    public Comment(String content, int parent, int articleIdx, int usersIdx){
        this.content = content;
        this.parent = parent;
        this.articleIdx = articleIdx;
        this.usersIdx = usersIdx;
        this.cmtdate = LocalDateTime.now();

    }

}

