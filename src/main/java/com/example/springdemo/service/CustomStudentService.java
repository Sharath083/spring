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

@Service
public class CustomStudentService implements UserDetailsService {
//    @Autowired
//    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            var userData=userRepo.findByName(username);
            if (userData == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
        String encodedPassword = passwordEncoder.encode(userData.getId().toString());
        System.out.println("--------------in load------------------"+encodedPassword);

        return User
                .withUsername(username)
                .password(encodedPassword)
                .roles("USER")
                .build();
        }

}
