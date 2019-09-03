package com.spl.spl.service.walk;

import com.spl.spl.dto.walk.Walk;
import com.spl.spl.repository.walk.WalkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class WalkServiceImpl implements WalkService {
    private WalkRepository workrepository;

    @Override
    public List findAll() {
        return workrepository.findAll();
    }

    @Override
    public Optional<Walk> findByIdx(Integer idx) {
        return workrepository.findById(idx);
    }

    @Override
    public void insert(double distance, double goal, LocalDate date) {
        workrepository.save(Walk.builder()
                .distance(distance)
                .goal(goal)
                .date(date)
                .build());
    }
}
