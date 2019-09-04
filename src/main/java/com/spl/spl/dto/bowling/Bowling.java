package com.spl.spl.dto.bowling;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;



@Entity
@Data
@NoArgsConstructor
public class Bowling {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bowling_idx")
    private Integer bowlingIdx;

    @Column(name = "spare")
    private int spare;

    @Column(name = "strike")
    private int strike;

    @Column(name ="perfectgame" )
    private int perfectgame;

    @Column(name = "total")
    private  int total;

    @Column
    private LocalDate bowlingdate;

    @Builder
    public Bowling(int spare, int strike, int perfectgame, int total){
        this.spare = spare;
        this.strike = strike;
        this.perfectgame = perfectgame;
        this.total = total;
        this.bowlingdate = LocalDate.now();

    }
}
