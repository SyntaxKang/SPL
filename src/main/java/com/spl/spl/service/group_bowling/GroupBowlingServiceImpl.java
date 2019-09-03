package com.spl.spl.service.group_bowling;

import com.spl.spl.dto.bowling.Bowling;
import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.group_bowling.Group_bowling;
import com.spl.spl.dto.users.Users;
import com.spl.spl.repository.Bowling.BowlingRepository;
import com.spl.spl.repository.group_bowling.GroupBowlingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupBowlingServiceImpl implements GroupBowlingService{
    private GroupBowlingRepository groupBowlingRepository;

    private BowlingRepository bowlingRepository;

    public List findAll(){
        return groupBowlingRepository.findAll();
    }

    @Override
    public void insert(Groups groups, Users users, Bowling bowling) {
        groupBowlingRepository.save(Group_bowling.builder()
                .groups(groups)
                .users(users)
                .bowling(bowling)
                .build());
    }

    @Override
    public List findByGroupIdx(int idx) {
        return groupBowlingRepository.findByGroupIdx(idx);
    }
}
