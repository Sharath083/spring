package com.example.springdemo.repository;

import com.example.springdemo.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepo extends JpaRepository<UserDetails, UUID> {
    UserDetails findByName(String name);
}
