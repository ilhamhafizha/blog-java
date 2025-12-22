package org.example.blog.services;

import org.example.blog.properties.SecretProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    SecretProperties secretProperties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || !username.equals(secretProperties.getUserUsername())) {
            throw new UsernameNotFoundException("not found");
        }
        return User.builder()
                .username(secretProperties.getUserUsername())
                .password(secretProperties.getUserPassword())
                .build();
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if (username == null |) {
//            throw new UsernameNotFoundException("username is null");
//        }
//        return User.builder()
//                .username(username)
//                .password("$2y$10$NP3xuI3p.ndkbUivZcoaQeMGF5kTvsBffrL1UK25dsw5ASUJ0CwwG")
//                .build();
//    }
}
