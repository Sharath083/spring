package com.example.springdemo.controller;

import com.example.springdemo.dto.AuthRequest;
import com.example.springdemo.dto.UserReq;
import com.example.springdemo.entity.UserDetails;
import com.example.springdemo.helper.HelperFunctions;
import com.example.springdemo.jwt.JwtHelper;
import com.example.springdemo.jwt.JwtRequest;
import com.example.springdemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserRoute {
    @Autowired
    private UserService userService;
    @Autowired
    private HelperFunctions helperFunctions;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/login")
    public HashMap<String,String> authenticateStudent(@RequestBody AuthRequest request){

        JwtRequest details = userService.getUserIdAndName(request.getUsername());
        helperFunctions.doAuthentication(request.getUsername(),details.getUserId());
        System.out.println("in router login");
        org.springframework.security.core.userdetails.UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtHelper.generateToken(userDetails.getUsername(),details.getUserId());
        HashMap<String,String> map=new HashMap<>();
        map.put("token",token);
        return map;

    }
    @PostMapping("/signup")
    public UserDetails signUp(@RequestBody @Valid UserReq req){
        return userService.signUp(req);
    }
    @GetMapping("/name/{name}")
    public UserDetails getUserByName(@PathVariable("name") String name){
        return userService.getUser(name);
    }
    @GetMapping("/name")
    public UserDetails getUserById(@RequestBody AuthRequest request){
        System.out.println("in getUserByName");
        return userService.getUser(request.getUsername());
    }
}
