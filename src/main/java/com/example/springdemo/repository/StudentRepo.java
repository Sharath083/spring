package com.example.springdemo.repository;

import com.example.springdemo.entity.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface StudentRepo  extends JpaRepository<StudentDetails, UUID> {
}
