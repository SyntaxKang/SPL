package com.spl.spl.service.group;

import com.spl.spl.dto.group.Groups;
import com.spl.spl.repository.group.GroupsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService{
    private GroupsRepository repository;

    public List findAll(){
        return repository.findAll();
    }

    @Override
    public Groups findByIdx(Integer idx) {
        return repository.findByIdx(idx);
    }
}
