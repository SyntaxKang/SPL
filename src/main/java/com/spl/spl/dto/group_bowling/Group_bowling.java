package com.spl.spl.dto.group_bowling;

import com.spl.spl.dto.bowling.Bowling;
import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.users.Users;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Group_bowling {
    @Id
    @Column(name = "group_bowling_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupBowlingIdx;

    @ManyToOne
    @JoinColumn(name = "groups_idx")
    private Groups groups;

    @ManyToOne
    @JoinColumn(name = "users_idx")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "bowling_idx")
    private Bowling bowling;

    @Builder
    public Group_bowling(Groups groups, Users users, Bowling bowling){
        this.groups = groups;
        this.users = users;
        this.bowling = bowling;
    }
}
