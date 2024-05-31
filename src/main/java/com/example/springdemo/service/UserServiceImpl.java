package com.example.springdemo.service;

import com.example.springdemo.dto.UserReq;
import com.example.springdemo.entity.UserDetails;
import com.example.springdemo.exception.CommonException;
import com.example.springdemo.jwt.JwtAuthenticationFilter;
import com.example.springdemo.jwt.JwtHelper;
import com.example.springdemo.jwt.JwtRequest;
import com.example.springdemo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepo  userRepo;
    @Autowired
    private JwtHelper jwtHelper;
    @Override
    public UserDetails signUp(UserReq data) {
        UserDetails user= UserDetails.build(
                UUID.randomUUID(),data.getName(),data.getAge(), data.getEmail(), data.getMobileNumber(),
                data.getPassword()
        );
        return userRepo.save(user);
    }
    @Override
    public JwtRequest getUserIdAndName(String username) throws UsernameNotFoundException {
        var userData=userRepo.findByName(username);
        if (userData == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return JwtRequest.build(
                userData.getName(), userData.getId().toString());
    }

    @Override
    public UserDetails getUser(String name) throws CommonException {
        var response=userRepo.findByName(name);
        if(response==null){
            throw new CommonException("001","User with name "+name+" Not Found");
        }
        var s=jwtHelper.getUsernameFromToken(JwtAuthenticationFilter.token);
        System.out.println(s+"uuid from token");
        return userRepo.findById(UUID.fromString(s.getUserId())).orElse(null);
    }


}
