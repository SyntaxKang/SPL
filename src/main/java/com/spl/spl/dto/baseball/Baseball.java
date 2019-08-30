package com.spl.spl.dto.baseball;



import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Baseball {

    @Id
    @Column(name = "baseball_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer baseballIdx;

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
    public Baseball(int atbat, int hit, int ball, int homerun, int rbi, int score, int strike){
        this.atbat = atbat;
        this.ball = ball;
        this.hit = hit;
        this.homerun = homerun;
        this.rbi = rbi;
        this.score = score;
        this.strike = strike;
    }
}
