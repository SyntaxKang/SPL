package com.spl.spl.dto.users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Users {

    @Id
    @Column(name="users_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usersIdx;

    @Column(name = "email")
    private String email;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "social_type")
    private String socialType;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "account_status")
    private Boolean accountStatus;

    @Column(name = "religion")
    private String religion;

    @Column(name = "profile")
    private String profile;

    @Column
    private String name;

    @Column
    private String nick;

    @Column(name = "find_key")
    private String findKey;

    @Builder
    public Users(Integer usersIdx,String email,String pwd,String socialType,Date birthDate,String gender,String religion,String profile,String name,String nick,String findKey){
        this.usersIdx = usersIdx;
        this.email = email;
        this.pwd = pwd;
        this.socialType = socialType;
        this.birthDate = birthDate;
        this.gender = gender;
        this.religion = religion;
        this.profile = profile;
        this.name = name;
        this.nick = nick;
        this.findKey = findKey;
        this.createDate = LocalDate.now();
        this.accountStatus = true;
    }
}
