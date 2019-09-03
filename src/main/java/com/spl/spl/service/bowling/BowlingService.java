package com.spl.spl.service.bowling;

import com.spl.spl.dto.bowling.Bowling;

import java.util.List;

public interface BowlingService {
     List findAll();
     Bowling findByIdx(int idx);
     void insert(int spare, int strike, int perfectgame, int total);
}
