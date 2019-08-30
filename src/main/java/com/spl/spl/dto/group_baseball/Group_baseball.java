package com.spl.spl.dto.group_baseball;

import com.spl.spl.dto.baseball.Baseball;
import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.users.Users;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Group_baseball {

    @Id
    @Column(name = "group_baseball_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupBaseballIdx;

    @ManyToOne
    @JoinColumn(name = "groups_idx")
    private Groups groups;

    @ManyToOne
    @JoinColumn(name = "users_idx")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "baseball_idx")
    private Baseball baseball;

    @Builder
    public Group_baseball(Groups groups,Users users,Baseball baseball){
        this.groups = groups;
        this.users = users;
        this.baseball = baseball;
    }
}
