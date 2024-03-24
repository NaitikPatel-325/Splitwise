package com.naitik.splitwise.security.services;


import com.naitik.splitwise.daojpa.UserDao;
import com.naitik.splitwise.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDao userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username) ;
        if(user == null){
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }


        return UserDetailsImpl.build(user);
    }


}
