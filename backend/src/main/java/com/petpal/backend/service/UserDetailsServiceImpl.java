package com.petpal.backend.service;

import com.petpal.backend.domain.User;
import com.petpal.backend.util.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private CustomPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = new User();
       user.setUsername(user.getUsername());

       user.setPassword(passwordEncoder.getPasswordEncoder().encode("asdfasdf"));
       user.setId(1L);
       return user;
    }
}
