package com.spl.spl.service.soccer;


import com.spl.spl.dto.soccer.Soccer;

import java.util.List;

public interface SoccerService {
     List findAll();
     Soccer findByIdx(Integer idx);

     void insert(int goal, int assist, int shoot, int foul, int yellowcard, int redcard, int corner, int penalty, int offside, int game);


}
