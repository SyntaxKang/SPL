package com.spl.spl.service.walk;

import com.spl.spl.dto.walk.Walk;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WalkService {


    public List findAll();
    public Optional<Walk> findByIdx(Integer idx);
    public void insert(double range, double goal, LocalDateTime date);
}
