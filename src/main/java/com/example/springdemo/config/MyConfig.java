package com.example.springdemo.config;

import com.example.springdemo.contants.Constants;
import com.example.springdemo.helper.Crypto;
import com.example.springdemo.redisConfig.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MyConfig {
    @Autowired
    private Crypto crypto;
    @Autowired
    private RedisHelper redisHelper;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    public String generateRedisToken(String id, String name) {
        var key=crypto.encrypt(id+"//"+name);
        redisHelper.set(Constants.REDIS_KEY+id,key);
        return key;
    }
}
