package com.spl.spl.service.baseball;

import com.spl.spl.dto.baseball.Baseball;
import com.spl.spl.repository.baseball.BaseballRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BaseballServiceImpl implements BaseballService {

    private BaseballRepository repository;

    @Override
    public List findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Baseball> findByIdx(Integer idx) {
        return repository.findById(idx);
    }

    @Override
    public void insert(int atbat, int hit, int ball, int homerun, int rbi, int score, int strike) {
        repository.save(Baseball.builder()
                .atbat(atbat)
                .ball(ball)
                .hit(hit)
                .homerun(homerun)
                .rbi(rbi)
                .score(score)
                .strike(strike)
                .build());
    }
}
