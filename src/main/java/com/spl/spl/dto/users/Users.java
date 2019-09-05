package com.spl.spl.dto.users;

import com.spl.spl.dto.users.enums.SocialType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class Users implements Serializable {

    @Id
    @Column(name="users_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usersIdx;


    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String nickname;

    @Column
    private String category;

    @Column
    private String pincipal;

    @Column
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column
    private String area;

    @Column
    private String areaa;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;



    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<UserAuthority> authorities = new HashSet<>();


    @Builder
    public Users(Integer usersIdx, String name, String password, String email, String pincipal, SocialType socialType, String nickname, String areaa, String area, String category, LocalDateTime createdDate, LocalDateTime updatedDate){
        this.usersIdx = usersIdx;
        this.name=name;
        this.password=password;
        this.email=email;
        this.nickname=nickname;
        this.area=area;
        this.areaa=areaa;
        this.socialType=socialType;
        this.pincipal=pincipal;
        this.category=category;
        this.createdDate=createdDate;
        this.updatedDate=updatedDate;
    }
}
