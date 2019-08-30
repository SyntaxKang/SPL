package com.spl.spl.service.users;

import com.spl.spl.dto.users.Users;
import com.spl.spl.repository.users.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    private UsersRepository repository;

    public List findAll(){
        return repository.findAll();
    }

    @Override
    public Users findByIdx(Integer idx) {
        return repository.findByIdx(idx);
    }
}
