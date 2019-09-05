package com.spl.spl.service.users;

import com.spl.spl.dto.users.Users;
import com.spl.spl.repository.users.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService, UserDetailsService {

    private UsersRepository repository;

    @Override
    public Users findByIdx(Integer idx) {
        return repository.findByIdx(idx);
    }


    static class UserDetailImpl extends User {
        public UserDetailImpl(Users user){
            super(user.getEmail(),user.getPassword(), user.getAuthorities());
        }
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

      /* com.spl.spl.domain.User user = userRepository.findByEmail(email);
            if(user == null){
                throw new UsernameNotFoundException(email);
           }
          return new UserDetailImpl(user);*/
        return Optional.ofNullable(repository.findByEmail(email))
                .map(UserDetailImpl::new)
                .orElseThrow(()->new UsernameNotFoundException(email));
    }

    public List<Users> findAll(){
        return repository.findAll();
    }


    public Page<Users> findAll(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());
        return repository.findAll(pageable);
    }
}
