package com.spl.spl.service.users;

import com.spl.spl.dto.users.Users;
import org.springframework.stereotype.Service;

public interface UsersService {
    public Users findByIdx(Integer idx);

    Users findByEmail(String email);
}
