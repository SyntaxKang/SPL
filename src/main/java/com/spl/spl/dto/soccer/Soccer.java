package com.spl.spl.dto.soccer;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
public class Soccer {

    @Id
    @Column(name = "soccerIdx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer soccerIdx;

    @Column(name = "goal")
    private int goal;

    @Column(name = "assist")
    private int assist;

    @Column(name = "shoot")
    private int shoot;

    @Column(name = "foul")
    private int foul;

    @Column(name = "yellowcard")
    private int yellowcard;

    @Column(name = "redcard")
    private int redcard;

    @Column(name = "corner")
    private int corner;

    @Column(name = "penalty")
    private int penalty;

    @Column(name = "offside")
    private int offside;

    @Column(name = "game")
    private int game;


    @Builder
    public Soccer(int goal, int assist, int shoot, int foul, int yellowcard, int redcard, int corner, int penalty, int offside, int game){
        this.goal = goal;
        this.assist = assist;
        this.shoot = shoot;
        this.foul = foul;
        this.yellowcard = yellowcard;
        this.redcard = redcard;
        this.corner = corner;
        this.penalty=penalty;
        this.offside=offside;
        this.game=game;
    }

}
