package com.spl.spl.dto.group_walk;

import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.users.Users;
import com.spl.spl.dto.walk.Walk;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Group_walk {


    @Id
    @Column(name = "group_walk_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupWalkIdx;

    @ManyToOne
    @JoinColumn(name = "groups_idx")
    private Groups groups;

    @ManyToOne
    @JoinColumn(name = "users_idx")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "walk_idx")
    private Walk walk;

    @Builder
    public Group_walk(Groups groups, Users users, Walk walk){
        this.groups = groups;
        this.users = users;
        this.walk = walk;
    }
}
