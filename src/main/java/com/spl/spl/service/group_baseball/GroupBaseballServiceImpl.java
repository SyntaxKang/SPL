package com.spl.spl.service.group_baseball;

import com.spl.spl.dto.baseball.Baseball;
import com.spl.spl.dto.group.Groups;
import com.spl.spl.dto.group_baseball.Group_baseball;
import com.spl.spl.dto.users.Users;
import com.spl.spl.repository.baseball.BaseballRepository;
import com.spl.spl.repository.group_baseball.GroupBaseballRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupBaseballServiceImpl implements GroupBaseballService{

    private GroupBaseballRepository repository;

    private BaseballRepository baseballRepository;

    public List findAll(){
        return repository.findAll();
    }

    @Override
    public void insert(Groups groups, Users users, Baseball baseball) {
        repository.save(Group_baseball.builder()
                .groups(groups)
                .users(users)
                .baseball(baseball)
                .build());
    }
}
