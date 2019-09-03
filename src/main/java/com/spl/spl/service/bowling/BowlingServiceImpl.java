package com.spl.spl.service.bowling;

import com.spl.spl.dto.bowling.Bowling;
import com.spl.spl.repository.Bowling.BowlingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BowlingServiceImpl implements BowlingService {

    private BowlingRepository bowlingRepository;

    @Override
    public List findAll() {
        return bowlingRepository.findAll();
    }

    @Override
    public Bowling findByIdx(int idx) {
        return bowlingRepository.findByIdx(idx);
    }

    @Override
    public void insert(int spare, int strike, int perfectgame, int total) {
        bowlingRepository.save(Bowling.builder()
                .spare(spare)
                .strike(strike)
                .perfectgame(perfectgame)
                .total(total)
                .build());
    }
}
