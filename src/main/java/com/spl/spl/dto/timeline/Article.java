package com.spl.spl.dto.timeline;

import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.users.Users;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_idx")
    private Integer articleIdx;

    @ManyToOne
    @JoinColumn(name = "groups_idx")
    private Groups groups;

    @ManyToOne
    @JoinColumn(name = "users_idx")
    private Users users;

    @Column(name = "content")
    private String content;

    @Column(name = "write_date")
    private LocalDateTime wrtieDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "n_check")
    private boolean nCheck;

    @Builder
    public Article(Groups groups,Users users,String content,boolean nCheck){
        this.groups = groups;
        this.users = users;
        this.content = content;
        this.wrtieDate = LocalDateTime.now();
        this.updateDate = null;
        this.nCheck = nCheck;
    }
}
