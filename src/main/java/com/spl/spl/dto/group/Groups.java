package com.spl.spl.dto.group;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Groups {

    @Id
    @Column(name = "groups_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupIdx;

    @Column(name = "name")
    private String name;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "content")
    private String content;

    @Column(name = "profile")
    private String profile;

    @Column(name = "religion")
    private String religion;

    @Column(name = "category")
    private String category;


    @Builder
    public Groups(Integer groupIdx,String name, String content, String profile, String religion, String category){
        this.groupIdx = groupIdx;
        this.name = name;
        this.content = content;
        this.profile = profile;
        this.religion = religion;
        this.category = category;
        this.createDate = LocalDate.now();
    }
}
