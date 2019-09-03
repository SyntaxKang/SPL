package com.spl.spl.dto.walk;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Walk {

    @Id
    @Column(name = "walk_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer walkidx;

    @Column(name="distance")
    private double distance;

    @Column(name="goal")
    private double goal;

    @Column(name="date")
    private LocalDate date;

    @Builder
    public Walk(double distance, double goal, LocalDate date)
    {
        this.distance=distance;
        this.goal=goal;
        this.date=date;

    }

}
