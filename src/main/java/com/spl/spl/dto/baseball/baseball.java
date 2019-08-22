package com.spl.spl.dto.baseball;



import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class baseball {

    public baseball(){};

    @Id
    @Column(name = "baseball_idx")
    @GeneratedValue
    private int baseball_idx;

    @Column(name = "game")
    private int game;

    @Column(name = "atbat")
    private int atbat;

    @Column(name = "hit")
    private int hit;

    @Column(name = "homerun")
    private int homerun;

    @Column(name = "rbi")
    private int rbi;

    @Column(name = "score")
    private int score;

    @Column(name = "ball")
    private int ball;

    @Column(name = "strike")
    private int strike;

    @Builder
    public baseball(int game, int atbat, int hit, int ball, int homerun, int rbi, int score, int strike){
        this.atbat = atbat;
        this.ball = ball;
        this.game = game;
        this.hit = hit;
        this.homerun = homerun;
        this.rbi = rbi;
        this.score = score;
        this.strike = strike;
    }
}
