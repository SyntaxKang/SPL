package com.spl.spl.dto.walk;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Walk {

    @Id
    @Column(name = "walk_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer walkidx;

    @Column(name="range")
    private double range;
    @Column(name="goal")
    private double goal;
    @Column(name="date")
    private LocalDateTime date;

    @Builder
    public Walk(double range, double goal, LocalDateTime date)
    {
        this.range=range;
        this.goal=goal;
        this.date=date;

    }

}
