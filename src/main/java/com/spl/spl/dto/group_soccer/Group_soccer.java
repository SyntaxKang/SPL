package com.spl.spl.dto.group_soccer;

import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.soccer.Soccer;
import com.spl.spl.dto.users.Users;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Group_soccer {

    @Id
    @Column(name = "group_soccer_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupSoccerIdx;

    @ManyToOne
    @JoinColumn(name = "groups_idx")
    private Groups groups;

    @ManyToOne
    @JoinColumn(name = "users_idx")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "soccer_idx")
    private Soccer soccer;

    @Builder
    public Group_soccer(Groups groups, Users users, Soccer soccer){
        this.groups = groups;
        this.users = users;
        this.soccer = soccer;
    }
}
