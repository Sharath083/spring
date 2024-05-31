package com.example.springdemo.controller;

import com.example.springdemo.entity.StudentDetails;
import com.example.springdemo.repository.StudentRepo;
import com.example.springdemo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentRoute {
    @Autowired
    StudentService studentService;
    @Autowired
    StudentRepo studentRepo;
    @PostMapping("/register")
    public StudentDetails createStudent(@RequestBody @Valid StudentDetails studentDetails){
        return (studentService.studentRegister(studentDetails));

    }
    @GetMapping("/id/{sid}")
    public StudentDetails getStudentData(@PathVariable("sid") UUID id){
        return studentService.getStudentData(id);
    }
    @PostMapping("/add")
    public StudentDetails addCourseToStudent(@RequestBody StudentDetails studentDetails){
        return studentService.addCourseToStudent(studentDetails);
    }
    @GetMapping("/name/{name}")
    public StudentDetails getStudentByName(@PathVariable("name") String name){
        return studentService.getStudentByName(name);
    }



}
