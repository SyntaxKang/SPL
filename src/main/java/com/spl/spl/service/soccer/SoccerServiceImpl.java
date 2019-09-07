package com.spl.spl.service.soccer;



import com.spl.spl.dto.soccer.Soccer;
import com.spl.spl.repository.soccer.SoccerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SoccerServiceImpl implements SoccerService{

    private SoccerRepository soccerRepository;

    @Override
    public List findAll() {
        return soccerRepository.findAll();
    }

    @Override
    public Soccer findByIdx(Integer idx) {
        return soccerRepository.findByIdx(idx);
    }

    @Override
    public void insert(int goal, int assist, int shoot, int foul, int yellowcard, int redcard, int corner,int penalty,int offside,int game) {
        soccerRepository.save(Soccer.builder()
                .goal(goal)
                .assist(assist)
                .shoot(shoot)
                .foul(foul)
                .yellowcard(yellowcard)
                .redcard(redcard)
                .corner(corner)
                .penalty(penalty)
                .offside(offside)
                .game(game)
                .build());
    }


}
