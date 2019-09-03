package com.spl.spl.service.group_bowling;

import com.spl.spl.dto.bowling.Bowling;
import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.users.Users;

import java.util.List;

public interface GroupBowlingService {
    public List findAll();

    public void insert(Groups groups, Users users, Bowling bowling);

    public List findByGroupIdx(int idx);
}
