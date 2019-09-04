package com.spl.spl.dto.photo;

import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.timeline.Article;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_idx")
    private Integer photoIdx;

    @ManyToOne
    @JoinColumn(name = "groups_idx")
    private Groups groups;

    @ManyToOne
    @JoinColumn(name = "article_idx")
    private Article article;

    @Column(name = "file_name")
    private String fileName;

    @Builder
    public Photo(Groups groups, Article article, String fileName) {
        this.groups = groups;
        this.article = article;
        this.fileName = fileName;
    }
}
