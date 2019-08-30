package com.spl.spl.service.user_group;

import com.spl.spl.dto.baseball.Baseball;
import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.group_baseball.Group_baseball;
import com.spl.spl.dto.users.Users;
import com.spl.spl.repository.user_group.UsersGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersGroupServiceImpl implements UsersGroupService{

    private UsersGroupRepository repository;

    public List findAll(){
        return repository.findAll();
    }

    @Override
    public List findByGroup(int groupIdx) {
        return repository.findByGroupIdx(groupIdx);
    }
}
