package com.spl.spl.service.group_baseball;

import com.spl.spl.dto.baseball.Baseball;
import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.users.Users;

import java.util.List;

public interface GroupBaseballService {
    public List findAll();

    public void insert(Groups groups, Users users, Baseball baseball);

    public List findByGroupIdx(Integer idx);
}
