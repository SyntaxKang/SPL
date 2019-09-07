package com.spl.spl.service.group_soccer;



import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.soccer.Soccer;
import com.spl.spl.dto.users.Users;

import java.util.List;

public interface GroupSoccerService {
    public List findAll();

    public void insert(Groups groups, Users users, Soccer soccer);

    public List findByGroupIdx(Integer idx);
}
