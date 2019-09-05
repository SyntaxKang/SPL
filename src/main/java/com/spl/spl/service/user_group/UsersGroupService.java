package com.spl.spl.service.user_group;


import java.util.List;

public interface UsersGroupService {
    public List findByGroup(int groupIdx);

    public List findByUserIdx(Integer userIdx);
}
