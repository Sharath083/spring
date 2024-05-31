package com.example.springdemo.service;

import com.example.springdemo.dto.UserReq;
import com.example.springdemo.entity.UserDetails;
import com.example.springdemo.jwt.JwtRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDetails signUp(UserReq data);
    UserDetails getUser(String name);
    JwtRequest getUserIdAndName(String username) throws UsernameNotFoundException;
}
