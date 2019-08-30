package com.spl.spl.dto.user_group;

import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.users.Users;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "users_group")
public class Users_group {

    @Id
    @Column(name="users_group_idx")
    @GeneratedValue
    private Integer usersGroupIdx;

    @ManyToOne
    @JoinColumn(name = "users_idx")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "groups_idx")
    private Groups groups;

    @Column
    private int grade;

    @Column
    private boolean ban;

    @Column
    private String nick;

    Users_group(){}

    @Builder
    public Users_group(Users users,Groups groups,int grade,String nick){
        this.users = users;
        this.groups = groups;
        this.grade = grade;
        this.ban = false;
        this.nick = nick;
    }
}
