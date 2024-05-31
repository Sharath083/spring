package com.example.springdemo.repository;

import com.example.springdemo.entity.Course;
import com.example.springdemo.entity.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CourseRepo extends JpaRepository<Course, UUID> {
    List<Course> findByNameContaining(String name);

}
