package com.example.springdemo.service;

import com.example.springdemo.entity.StudentDetails;

import java.util.UUID;

public interface StudentService {
    public StudentDetails studentRegister(StudentDetails name);
    public StudentDetails getStudentData(UUID id);
}
