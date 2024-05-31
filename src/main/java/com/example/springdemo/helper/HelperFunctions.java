package com.example.springdemo.helper;

import com.example.springdemo.jwt.JwtRequest;
import com.example.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class HelperFunctions {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    public void doAuthentication(String name,String email){

        UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(name,email);
        try {
            authenticationManager.authenticate(auth);
        }catch (BadCredentialsException e){
            throw new BadCredentialsException(" Invalid name or email !!");
        }
    }
}
