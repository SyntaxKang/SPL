package com.spl.spl.service.group_walk;

import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.group_walk.Group_walk;
import com.spl.spl.dto.users.Users;
import com.spl.spl.dto.walk.Walk;
import com.spl.spl.repository.group_walk.GroupWalkRepository;
import com.spl.spl.repository.walk.WalkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupWalkServiceImpl implements GroupWalkService {
    private GroupWalkRepository repository;

    private WalkRepository walkRepository;

    public List findAll(){
        return repository.findAll();
    }

    @Override
    public void insert(Groups groups, Users users, Walk walk) {
        repository.save(Group_walk.builder()
                .groups(groups)
                .users(users)
                .walk(walk)
                .build());
    }

    @Override
    public List findByGroupIdx(Integer idx) {
        return repository.findByGroupIdx(idx);
    }
}
