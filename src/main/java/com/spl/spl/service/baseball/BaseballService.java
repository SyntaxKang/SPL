package com.spl.spl.service.baseball;

import com.spl.spl.dto.baseball.Baseball;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface BaseballService {
    public List findAll();
    public Optional<Baseball> findByIdx(Integer idx);
    public void insert(int atbat, int hit, int ball, int homerun, int rbi, int score, int strike);
}
