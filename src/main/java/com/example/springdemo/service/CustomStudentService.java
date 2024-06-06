package com.example.springdemo.service;


import com.example.springdemo.jwt.JwtRequest;
import com.example.springdemo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomStudentService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        var userData = userRepo.findById(UUID.fromString(userId)).orElse(null);
        if (userData == null) {
            throw new UsernameNotFoundException("User not found with id: " + userId);
        } else {
            String encodedPassword = passwordEncoder.encode(userData.getId().toString());
            return User
                    .withUsername(userData.getName())
                    .password(encodedPassword)
                    .roles("USER")
                    .build();
        }
    }

}
