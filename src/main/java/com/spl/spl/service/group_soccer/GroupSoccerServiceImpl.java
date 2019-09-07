package com.spl.spl.service.group_soccer;


import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.group_soccer.Group_soccer;
import com.spl.spl.dto.soccer.Soccer;
import com.spl.spl.dto.users.Users;
import com.spl.spl.repository.group_soccer.GroupSoccerRepository;
import com.spl.spl.repository.soccer.SoccerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupSoccerServiceImpl implements GroupSoccerService {

    private GroupSoccerRepository repository;

    private SoccerRepository soccerRepository;

    public List findAll(){
        return repository.findAll();
    }

    @Override
    public void insert(Groups groups, Users users, Soccer soccer) {
        repository.save(Group_soccer.builder()
                .groups(groups)
                .users(users)
                .soccer(soccer)
                .build());
    }

    @Override
    public List findByGroupIdx(Integer idx) {
        return repository.findByGroupIdx(idx);
    }
}
