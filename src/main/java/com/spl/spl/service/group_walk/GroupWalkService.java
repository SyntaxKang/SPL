package com.spl.spl.service.group_walk;

import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.users.Users;
import com.spl.spl.dto.walk.Walk;

import java.util.List;

public interface GroupWalkService {

    public List findAll();
    public void insert(Groups groups, Users users, Walk walk);
    public List findByGroupIdx(Integer idx);
}
